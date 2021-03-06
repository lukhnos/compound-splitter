package org.apache.lucene.analysis.de.compounds;

import java.io.*;
import org.apache.lucene.store.DataInput;

/**
 * A {@link DataInput} wrapping a plain {@link InputStream}.
 */
class InputStreamDataInput extends DataInput implements Closeable
{

    private final InputStream is;

    public InputStreamDataInput(InputStream is)
    {
        this.is = is;
    }

    @Override
    public void close() throws IOException
    {
        this.is.close();
    }

    @Override
    public byte readByte() throws IOException
    {
        int v = is.read();
        if (v == -1) throw new EOFException();
        return (byte) v;
    }

    @Override
    public void readBytes(byte[] b, int offset, int len) throws IOException
    {
        while (len > 0) {
            final int cnt = is.read(b, offset, len);
            len -= cnt;
            offset += cnt;
        }
    }
}
