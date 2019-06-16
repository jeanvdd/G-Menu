package com.orange.dgil.dao.impl.json;

import com.orange.dgil.bean.Unpexbin;
import com.orange.dgil.dao.UnpexbinReader;
import com.orange.tui.taplog.TapLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class UnpexbinJsonStreamReaderImpl implements UnpexbinReader {
    private final UnpexbinTypeReference listType = new UnpexbinTypeReference();
    private final ObjectMapper mapper = new ObjectMapper();
    private final InputStream stream;

    public UnpexbinJsonStreamReaderImpl(InputStream stream) {
        this.stream = stream;
    }

    public List<Unpexbin> read() throws IOException {
        InputStreamReader reader = new InputStreamReader(this.stream, "UTF-8");
        try {
            List<Unpexbin> list = (List) this.mapper.readValue(reader, this.listType);
            try {
                reader.close();
                return list;
            } catch (IOException e) {
                TapLog.e(this, e, new Object[]{"error while closing stream reader"});
                throw e;
            }
        } catch (IOException e2) {
            TapLog.e(this, e2, new Object[]{"error while reading json stream"});
            throw e2;
        } catch (Throwable th) {
            try {
                reader.close();
            } catch (IOException e22) {
                TapLog.e(this, e22, new Object[]{"error while closing stream reader"});
                throw e22;
            }
        }
    }
}
