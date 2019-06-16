package com.orange.dgil.dao.impl.json;

import com.orange.dgil.bean.Learning;
import com.orange.dgil.bean.Model;
import com.orange.dgil.bean.Symbol;
import com.orange.dgil.bean.linked.LinkedSymbol;
import com.orange.dgil.dao.ILearningDao;
import com.orange.tui.taplog.TapLog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class LearningDaoJsonImpl implements ILearningDao {
    private boolean autocommit = true;
    private final File file;
    private final Map<Long, JsonLearning> learnings = new HashMap();
    private final TypeReference<List<JsonLearning>> listType = new C00071();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, Model> modelsMapById = new HashMap();
    private long nextid;
    private final Map<Long, LinkedSymbol> symbolsMapById = new HashMap();

    /* renamed from: com.orange.dgil.dao.impl.json.LearningDaoJsonImpl$1 */
    class C00071 extends TypeReference<List<JsonLearning>> {
        C00071() {
        }
    }

    public LearningDaoJsonImpl(File file) throws IOException {
        this.file = file;
        if (!(file.exists() || file.createNewFile())) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("A pb occured while creating file %s", new Object[]{file.getAbsolutePath()});
            TapLog.w(this, objArr);
        }
        for (JsonLearning learnin : read()) {
            this.learnings.put(Long.valueOf(learnin.getId()), learnin);
            this.nextid = this.nextid > learnin.getId() ? this.nextid : learnin.getId();
            for (LinkedSymbol symbol : learnin.getSymbols()) {
                this.symbolsMapById.put(Long.valueOf(symbol.getId()), symbol);
                this.nextid = this.nextid > symbol.getId() ? this.nextid : symbol.getId();
                for (Model model : symbol.getModels()) {
                    long j;
                    this.modelsMapById.put(Long.valueOf(model.getId()), model);
                    if (this.nextid > model.getId()) {
                        j = this.nextid;
                    } else {
                        j = model.getId();
                    }
                    this.nextid = j;
                }
            }
        }
    }

    private List<JsonLearning> read() throws IOException {
        try {
            List<JsonLearning> result = (List) this.mapper.readValue(this.file, this.listType);
            if (result == null) {
                result = new ArrayList();
            }
            return result;
        } catch (IOException e) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("error while reading json file %s", new Object[]{this.file.getAbsolutePath()});
            TapLog.e(this, e, objArr);
            throw e;
        }
    }

    public void commit() throws IOException {
        try {
            this.mapper.writeValue(this.file, getLearnings());
        } catch (IOException e) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("error while writing json file %s", new Object[]{this.file.getAbsolutePath()});
            TapLog.e(this, e, objArr);
            throw e;
        }
    }

    public List<Learning> getLearnings() {
        return new ArrayList(this.learnings.values());
    }

    public Learning addLearning(Learning learning) throws IOException {
        long j = this.nextid;
        this.nextid = 1 + j;
        JsonLearning jsonLearinig = new JsonLearning((int) j, learning.getName());
        this.learnings.put(Long.valueOf(jsonLearinig.getId()), jsonLearinig);
        if (this.autocommit) {
            commit();
        }
        return jsonLearinig;
    }

    public List<Model> getModels(Symbol symbol) {
        return Collections.unmodifiableList(((LinkedSymbol) this.symbolsMapById.get(Long.valueOf(symbol.getId()))).getModels());
    }

    public Model addModel(Model proto, Symbol s) throws IOException {
        long j = this.nextid;
        this.nextid = 1 + j;
        ((LinkedSymbol) this.symbolsMapById.get(Long.valueOf(s.getId()))).getModels().add(new Model(j, proto.getStrokes()));
        if (this.autocommit) {
            commit();
        }
        return proto;
    }

    public void removeModel(Model proto, Symbol s) throws IOException {
        ((LinkedSymbol) this.symbolsMapById.get(Long.valueOf(s.getId()))).getModels().remove(proto);
        if (this.autocommit) {
            commit();
        }
    }

    public List<Symbol> getSymbols(Learning a) {
        return new ArrayList(((JsonLearning) this.learnings.get(Long.valueOf(a.getId()))).getSymbols());
    }

    public Symbol addSymbol(Symbol symbol, Learning learning) throws IOException {
        long j = this.nextid;
        this.nextid = 1 + j;
        LinkedSymbol jsonSymbol = new LinkedSymbol(j, symbol.getName(), symbol.getMshClass());
        ((JsonLearning) this.learnings.get(Long.valueOf(learning.getId()))).getSymbols().add(jsonSymbol);
        this.symbolsMapById.put(Long.valueOf(jsonSymbol.getId()), jsonSymbol);
        if (this.autocommit) {
            commit();
        }
        return jsonSymbol;
    }

    public void removeSymbol(Symbol s, Learning learning) throws IOException {
        ((JsonLearning) this.learnings.get(Long.valueOf(learning.getId()))).getSymbols().remove(s);
        if (this.autocommit) {
            commit();
        }
    }

    public void removeLearning(Learning a) throws IOException {
        this.learnings.remove(Long.valueOf(a.getId()));
        if (this.autocommit) {
            commit();
        }
    }

    public void updateSymbol(Symbol s) {
        LinkedSymbol jsonSymbol = (LinkedSymbol) this.symbolsMapById.get(Long.valueOf(s.getId()));
        jsonSymbol.setName(s.getName());
        jsonSymbol.setMshClass(s.getMshClass());
    }

    public void setAutocommit(boolean autocommit) {
        this.autocommit = autocommit;
    }
}
