package geekbrains.cloud.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

//msg - ByteBuf
@Slf4j
public class FirstHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        log.info("received: {}", message);
        StringBuilder builder = new StringBuilder();
        while (message.isReadable()){
            builder.append(message.readChar());
        }
        ctx.fireChannelRead(builder.toString());
    }
}