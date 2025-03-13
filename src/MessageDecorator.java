public abstract class MessageDecorator implements Message {
    public Message message;

    public MessageDecorator(Message message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage();
    }
}
