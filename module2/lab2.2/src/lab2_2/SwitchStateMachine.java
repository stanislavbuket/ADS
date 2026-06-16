package lab2_2;

public class SwitchStateMachine {
    
    public enum State {
        START,
        LESS_THAN,
        SIGN,
        DIGITS,
        LETTERS,
        ACCEPT,
        ERROR
    }

    public static boolean isValid(String word) {
        State state = State.START;
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            
            switch (state) {
                case START:
                    if (ch == '<') {
                        state = State.LESS_THAN;
                    } else {
                        state = State.ERROR;
                    }
                    break;
                    
                case LESS_THAN:
                    if (ch == '+' || ch == '-') {
                        state = State.SIGN;
                    } else {
                        state = State.ERROR;
                    }
                    break;
                    
                case SIGN:
                    if (ch >= '0' && ch <= '5') {
                        state = State.DIGITS;
                    } else if (ch >= 'P' && ch <= 'Z') {
                        state = State.LETTERS;
                    } else {
                        state = State.ERROR;
                    }
                    break;
                    
                case DIGITS:
                    if (ch >= '0' && ch <= '5') {
                        // Stay in DIGITS
                    } else if (ch == '>') {
                        state = State.ACCEPT;
                    } else {
                        state = State.ERROR;
                    }
                    break;
                    
                case LETTERS:
                    if (ch >= 'P' && ch <= 'Z') {
                        // Stay in LETTERS
                    } else if (ch == '>') {
                        state = State.ACCEPT;
                    } else {
                        state = State.ERROR;
                    }
                    break;
                    
                case ACCEPT:
                    state = State.ERROR;
                    break;
                    
                case ERROR:
                    break;
            }
            
            if (state == State.ERROR) {
                return false;
            }
        }
        
        return state == State.ACCEPT;
    }
}
