package com.tsystems.javaschool.tasks.calculator;

import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        if (statement != null && !statement.isEmpty()) {
            try {
                statement += " ";
                LinkedList<Double> list = new LinkedList<>();
                LinkedList<Character> symbol = new LinkedList<>();
                for (int i = 0; i < statement.length(); i++) {
                    char character = statement.charAt(i);
                    if (isGap(character)) {
                        continue;
                    }
                    if (character == '(') {
                        symbol.add(character);
                    } else if (character == ')') {
                        while (symbol.getLast() != '(') {
                            process(list, symbol.removeLast());
                        }
                        symbol.removeLast();
                    } else if (isOperator(character)) {
                        while (!symbol.isEmpty() && priority(symbol.getLast()) >= priority(character)) {
                            process(list, symbol.removeLast());
                        }
                        symbol.add(character);
                    } else {
                        String operand = "";
                        while (i < statement.length() && Character.isDigit(statement.charAt(i)) || statement.charAt(i) == '.') {
                            operand += statement.charAt(i);
                            i++;
                        }
                        --i;
                        list.add(Double.parseDouble(operand));
                    }

                }
                while (!symbol.isEmpty()) {
                    process(list, symbol.removeLast());
                }
                double result = DoubleRounder.round(list.get(0), 4);
                if (result % 1 != 0.0 && !Double.isInfinite(result)) {
                    return "" + result;
                } else if (Double.isInfinite(result)) {
                    return null;
                } else {
                    int resultInt = (int) result;
                    return "" + resultInt;
                }

            } catch (NumberFormatException exc) {
                return null;
            } catch (NoSuchElementException exc) {
                return null;
            }
        } else {
            return null;
        }
    }

    boolean isGap(char symbol) {
        return symbol == ' ';
    }

    boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
    }

    int priority(char symbol) {
        switch (symbol) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    void process(LinkedList<Double> list, char symbol) {
        double last = list.removeLast();
        double nextToLast = list.removeLast();
        switch (symbol) {
            case '+':
                list.add(nextToLast + last);
                break;
            case '-':
                list.add(nextToLast - last);
                break;
            case '*':
                list.add(nextToLast * last);
                break;
            case '/':
                list.add(nextToLast / last);
                break;
        }
    }
}
