package lab2_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class TableStateMachine {
    
    public enum State {
        START,
        LESS_THAN,
        SIGN,
        DIGITS,
        LETTERS,
        ACCEPT,
        ERROR
    }

    public enum CharacterType {
        CHAR_LESS_THAN,
        CHAR_SIGN,
        CHAR_DIGIT,
        CHAR_LETTER,
        CHAR_GREATER_THAN,
        CHAR_OTHER
    }

    private static final Map<State, Map<CharacterType, State>> TRANSITION_TABLE = new EnumMap<>(State.class);

    static {
        for (State s : State.values()) {
            TRANSITION_TABLE.put(s, new EnumMap<>(CharacterType.class));
        }

        TRANSITION_TABLE.get(State.START).put(CharacterType.CHAR_LESS_THAN, State.LESS_THAN);
        TRANSITION_TABLE.get(State.LESS_THAN).put(CharacterType.CHAR_SIGN, State.SIGN);
        TRANSITION_TABLE.get(State.SIGN).put(CharacterType.CHAR_DIGIT, State.DIGITS);
        TRANSITION_TABLE.get(State.SIGN).put(CharacterType.CHAR_LETTER, State.LETTERS);
        TRANSITION_TABLE.get(State.DIGITS).put(CharacterType.CHAR_DIGIT, State.DIGITS);
        TRANSITION_TABLE.get(State.DIGITS).put(CharacterType.CHAR_GREATER_THAN, State.ACCEPT);
        TRANSITION_TABLE.get(State.LETTERS).put(CharacterType.CHAR_LETTER, State.LETTERS);
        TRANSITION_TABLE.get(State.LETTERS).put(CharacterType.CHAR_GREATER_THAN, State.ACCEPT);
    }

    private static CharacterType getCharType(char ch) {
        if (ch == '<') return CharacterType.CHAR_LESS_THAN;
        if (ch == '+' || ch == '-') return CharacterType.CHAR_SIGN;
        if (ch >= '0' && ch <= '5') return CharacterType.CHAR_DIGIT;
        if (ch >= 'P' && ch <= 'Z') return CharacterType.CHAR_LETTER;
        if (ch == '>') return CharacterType.CHAR_GREATER_THAN;
        return CharacterType.CHAR_OTHER;
    }

    public static boolean isValid(String word) {
        State state = State.START;
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            CharacterType type = getCharType(ch);
            state = TRANSITION_TABLE.get(state).getOrDefault(type, State.ERROR);
            if (state == State.ERROR) {
                return false;
            }
        }
        
        return state == State.ACCEPT;
    }

    public static void processFile(String filepath) {
        System.out.println("Processing file: " + filepath + " using Table FSM...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            
            String[] tokens = content.toString().split("[\\(\\)]+");
            
            for (String token : tokens) {
                token = token.trim();
                if (token.isEmpty()) continue;
                
                boolean valid = isValid(token);
                System.out.printf("  Token: %-15s -> %s\n", token, valid ? "VALID" : "INVALID");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
