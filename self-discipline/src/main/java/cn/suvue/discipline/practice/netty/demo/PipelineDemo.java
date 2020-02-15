package cn.suvue.discipline.practice.netty.demo;
/**
 * 链式的责任链模式，netty中基于这种模式
 *
 * @author suvue
 * @date 2020/2/15
 */
public class PipelineDemo{
    /**
     * 初始化的时候造一个head,但是没有实际的处理，只是作为责任链的开始
     */
    public HandlerChainContext head = new HandlerChainContext(new AbstractHandler() {
        @Override
        void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
            handlerChainContext.runNext(arg0);
        }
    });

    /**
     * 责任链的调用入口
     */
    public void requestProcess(Object arg) {
        this.head.handler(arg);
    }

    /**
     * 往链表的末尾加一个handler
     */
    public void addLast(AbstractHandler handler){
        HandlerChainContext context = head;
        while (context.next!=null){
            context = context.next;
        }
        context.next = new HandlerChainContext(handler);
    }

    public static void main(String[] args) {
        PipelineDemo pipelineChainDemo = new PipelineDemo();
        pipelineChainDemo.addLast(new Handler2());
        pipelineChainDemo.addLast(new Handler1());
        pipelineChainDemo.addLast(new Handler1());
        pipelineChainDemo.addLast(new Handler2());

        // 发起请求
        pipelineChainDemo.requestProcess("火车呜呜呜~~");

    }
}

/**
 * handler上下文，我主要负责维护链，和链的执行
 */
class HandlerChainContext {
    HandlerChainContext next; // 下一个节点
    AbstractHandler handler;

    public HandlerChainContext(AbstractHandler handler) {
        this.handler = handler;
    }

    void handler(Object arg0) {
        this.handler.doHandler(this, arg0);
    }

    /**
     * 继续执行下一个
     */
    void runNext(Object arg0) {
        if (this.next != null) {
            this.next.handler(arg0);
        }
    }
}

// 处理器抽象类
abstract class AbstractHandler {
    /**
     * 处理器，这个处理器就做一件事情，在传入的字符串中增加一个尾巴..
     */
    abstract void doHandler(HandlerChainContext handlerChainContext, Object arg0); // handler方法
}

// 处理器具体实现类
class Handler1 extends AbstractHandler {
    @Override
    void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
        arg0 = arg0.toString() + "..handler1的小尾巴.....";
        System.out.println("我是Handler1的实例，我在处理：" + arg0);
        // 继续执行下一个
        handlerChainContext.runNext(arg0);
    }
}

// 处理器具体实现类
class Handler2 extends AbstractHandler {
    @Override
    void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
        arg0 = arg0.toString() + "..handler2的小尾巴.....";
        System.out.println("我是Handler2的实例，我在处理：" + arg0);
        // 继续执行下一个
        handlerChainContext.runNext(arg0);
    }
}

