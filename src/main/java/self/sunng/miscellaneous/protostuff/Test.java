package self.sunng.miscellaneous.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * Created by abc on 15/12/29.
 */
public class Test {
    public static void main(String[] args) {
        Ent ent = new Ent();
        ent.setI(1);
        ent.setS("s");
        byte[] data = serializeProtoStuffReport(ent);
        Ent newent = deserializeProtoStuffReport(data);
        System.out.println(ent.getI());
        System.out.println(ent.getS());

        Another another = deserializeAnother(data);
        System.out.println(another.getI());
        System.out.println(another.getS());
        System.out.println(another.getSs());
    }


    public static byte[] serializeProtoStuffReport(Ent ent) {
        Schema<Ent> schema = RuntimeSchema
                .getSchema(Ent.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(ent, schema, buffer);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    public static Ent deserializeProtoStuffReport(byte[] bytesList) {
        Schema<Ent> schema = RuntimeSchema.getSchema(Ent.class);
        Ent ent = new Ent();
        ProtostuffIOUtil.mergeFrom(bytesList,ent,schema);
        return ent;
    }

    public static Another deserializeAnother(byte[] bytesList) {
        Schema<Another> schema = RuntimeSchema.getSchema(Another.class);
        Another another = new Another();
        ProtostuffIOUtil.mergeFrom(bytesList,another,schema);
        return another;
    }

}
