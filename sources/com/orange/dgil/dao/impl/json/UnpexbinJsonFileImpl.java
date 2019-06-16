package com.orange.dgil.dao.impl.json;

import com.orange.dgil.bean.Unpexbin;
import com.orange.dgil.dao.UnpexbinReader;
import com.orange.dgil.dao.UnpexbinWriter;
import com.orange.tui.taplog.TapLog;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class UnpexbinJsonFileImpl implements UnpexbinReader, UnpexbinWriter {
    private final File file;
    private final UnpexbinTypeReference listType = new UnpexbinTypeReference();
    private final ObjectMapper mapper = new ObjectMapper();

    public UnpexbinJsonFileImpl(File file) {
        this.file = file;
    }

    public void write(List<Unpexbin> unpexbins) throws IOException {
        try {
            this.mapper.writeValue(this.file, unpexbins);
        } catch (IOException e) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("error while writing json file %s", new Object[]{this.file.getAbsolutePath()});
            TapLog.e(this, e, objArr);
            throw e;
        }
    }

    public List<Unpexbin> read() throws IOException {
        try {
            return (List) this.mapper.readValue(this.file, this.listType);
        } catch (IOException e) {
            Object[] objArr = new Object[1];
            objArr[0] = String.format("error while reading json file %s", new Object[]{this.file.getAbsolutePath()});
            TapLog.e(this, e, objArr);
            throw e;
        }
    }
}
