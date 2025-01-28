public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override String toString() {
        return "[T]" + super.toString();
    }
}
