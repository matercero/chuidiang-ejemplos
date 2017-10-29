package com.chuidiang.examples;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;

/**
 * Created by chuidiang on 29/10/17.
 */
public class KryoEncoderHandler extends MessageToByteEncoder<Object> {
    private Kryo kryo = new Kryo();
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        System.out.println("encode");
        Output output = new Output(new byte[2000]);
        kryo.writeClassAndObject(output, msg);
        System.out.println(output.toBytes().length);
        out.writeInt(output.toBytes().length);
        out.writeBytes(output.toBytes());
    }
}
