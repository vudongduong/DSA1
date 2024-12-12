public class StudentStack {
    private Student[] stack;
    private int top;

    public StudentStack(int capacity) {
        stack = new Student[capacity];
        top = -1;
    }

    public void push(Student student) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full. Cannot push more students.");
        }
        stack[++top] = student;
    }

    public Student pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop any students.");
        }
        return stack[top--];
    }

    public Student peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }

    public int size() {
        return top + 1;
    }
}
