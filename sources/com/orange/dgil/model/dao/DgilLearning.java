package com.orange.dgil.model.dao;

import com.orange.dgil.model.bean.Learning;
import com.orange.dgil.model.bean.Model;
import com.orange.dgil.model.bean.Symbol;
import com.orange.dgil.model.bean.Unpexbin;
import java.io.IOException;
import java.util.List;

public final class DgilLearning implements ILearningDao {
    private static final DgilLearning INSTANCE = new DgilLearning();
    private static final String SYMBOL_ACCESS_ERROR = "symbol database access object not provided";
    private ILearningDao learningDao;
    private UnpexbinWriter writer;

    public static DgilLearning get() {
        return INSTANCE;
    }

    private DgilLearning() {
    }

    public ILearningDao getLearningDao() {
        return this.learningDao;
    }

    public void setLearningDao(ILearningDao learningDao) {
        this.learningDao = learningDao;
    }

    public void setUnpexbinWriter(UnpexbinWriter writer) {
        this.writer = writer;
    }

    public List<Learning> getLearnings() {
        if (this.learningDao != null) {
            return this.learningDao.getLearnings();
        }
        throw new UnsupportedOperationException("learning database access object not provided.");
    }

    public Learning addLearning(Learning learning) throws IOException {
        if (this.learningDao != null) {
            return this.learningDao.addLearning(learning);
        }
        throw new UnsupportedOperationException("learning database access object not provided.");
    }

    public List<Model> getModels(Symbol s) {
        if (this.learningDao != null) {
            return this.learningDao.getModels(s);
        }
        throw new UnsupportedOperationException("model database access object not provided.");
    }

    public Model addModel(Model proto, Symbol s) throws IOException {
        if (this.learningDao != null) {
            return this.learningDao.addModel(proto, s);
        }
        throw new UnsupportedOperationException("model database access object not provided.");
    }

    public void removeModel(Model proto, Symbol s) throws IOException {
        if (this.learningDao != null) {
            this.learningDao.removeModel(proto, s);
            return;
        }
        throw new UnsupportedOperationException("model database access object not provided.");
    }

    public List<Symbol> getSymbols(Learning a) {
        if (this.learningDao != null) {
            return this.learningDao.getSymbols(a);
        }
        throw new UnsupportedOperationException(SYMBOL_ACCESS_ERROR);
    }

    public Symbol addSymbol(Symbol symbol, Learning a) throws IOException {
        if (this.learningDao != null) {
            return this.learningDao.addSymbol(symbol, a);
        }
        throw new UnsupportedOperationException(SYMBOL_ACCESS_ERROR);
    }

    public void removeSymbol(Symbol s, Learning a) throws IOException {
        if (this.learningDao != null) {
            this.learningDao.removeSymbol(s, a);
            return;
        }
        throw new UnsupportedOperationException(SYMBOL_ACCESS_ERROR);
    }

    public void writeUnpexbins(List<Unpexbin> unpexbins) throws IOException {
        if (this.writer != null) {
            this.writer.write(unpexbins);
            return;
        }
        throw new UnsupportedOperationException("unpexbin database access object not provided.");
    }

    public void removeLearning(Learning a) throws IOException {
        if (this.learningDao != null) {
            this.learningDao.removeLearning(a);
            return;
        }
        throw new UnsupportedOperationException(SYMBOL_ACCESS_ERROR);
    }

    public void updateSymbol(Symbol s) {
        if (this.learningDao != null) {
            this.learningDao.updateSymbol(s);
            return;
        }
        throw new UnsupportedOperationException(SYMBOL_ACCESS_ERROR);
    }
}
