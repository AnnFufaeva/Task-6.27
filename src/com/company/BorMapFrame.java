package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BorMapFrame extends JFrame{
    private JPanel panel1;
    private JTextField keyTextField;
    private JTextField valueTextField;
    private JButton putButton;
    private JButton printAllKeysButton;
    private JTextArea printTextArea;
    private JButton gatValueButton;
    private JTextField getOrDeleteValueTextField;
    private JTextArea getValueTextArea;
    private JButton printKeysByPrefixButton;
    private JTextField keysByPrefixTextField;
    private JTextArea keysByPrefixTextArea;
    private JButton deleteValueButton;
    private JButton clearMapButton;
    private JTextArea exprintTextArea;
    private JTextArea exkeysByPrefixTextArea;
    private JTextArea exgetValueTextArea;

    public BorMapFrame(){
        this.setTitle("Task 2.6");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        keysByPrefixTextArea.setEditable(false);
        getValueTextArea.setEditable(false);
        printTextArea.setEditable(false);
        exkeysByPrefixTextArea.setEditable(false);
        exgetValueTextArea.setEditable(false);
        exprintTextArea.setEditable(false);

        MyBorMap<String> myMap = new MyBorMap<>();
        TreeMap<String, String> exMap = new TreeMap<>();

        clearMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myMap.clear();
                keysByPrefixTextArea.setText("");
                getValueTextArea.setText("");
                printTextArea.setText("");

                exMap.clear();
                exkeysByPrefixTextArea.setText("");
                exgetValueTextArea.setText("");
                exprintTextArea.setText("");

            }
        });
        putButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = keyTextField.getText();
                String value = valueTextField.getText();
                if (key.length() != 0 && value.length() != 0){
                    myMap.put(key, value);
                    exMap.put(key, value);
                }
            }
        });
        gatValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value;
                String exValue;
                String key = getOrDeleteValueTextField.getText();
                if (key.length() != 0) {
                    value = myMap.get(key);
                    exValue = exMap.get(key);
                    if (value != null) getValueTextArea.setText(value);
                    else getValueTextArea.setText("Key does not exist");
                    if (exValue != null) exgetValueTextArea.setText(exValue);
                    else exgetValueTextArea.setText("Key does not exist");
                }
            }
        });
        deleteValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = getOrDeleteValueTextField.getText();
                if (key.length() != 0) {
                    myMap.remove(key);
                    exMap.remove(key);
                }
            }
        });
        printAllKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    String keys = new String();
                    Set<String> keys0 = myMap.keySet();
                    Iterator iterator = keys0.iterator();
                    while (iterator.hasNext()) {
                        keys += iterator.next().toString() + "\n";
                    }
                    printTextArea.setText(keys);
                }
                {
                    String exkeys = new String();
                    Set<String> exkeys0 = exMap.keySet();
                    Iterator iterator = exkeys0.iterator();
                    while (iterator.hasNext()) {
                        exkeys += iterator.next().toString() + "\n";
                    }
                    exprintTextArea.setText(exkeys);
                }


            }
        });
        printKeysByPrefixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    String prefix = keysByPrefixTextField.getText();
                    String keys = new String();
                    Set<String> keys0 = myMap.keySet(prefix);
                    if (keys0 != null) {
                        Iterator iterator = keys0.iterator();
                        while (iterator.hasNext()) {
                            keys += iterator.next().toString() + "\n";
                        }
                    }

                    keysByPrefixTextArea.setText(keys);
                }
                {
                    String exprefix = keysByPrefixTextField.getText();
                    String exkeys = new String();
                    Set<String> exkeys0 = exprintByPrefix(exMap, exprefix);
                    if (exkeys0 != null) {
                        Iterator iterator = exkeys0.iterator();
                        while (iterator.hasNext()) {
                            exkeys += iterator.next().toString() + "\n";
                        }
                    }

                    exkeysByPrefixTextArea.setText(exkeys);
                }
            }
        });
    }
    private Set<String> exprintByPrefix(TreeMap exMap, String prefix){
        Set<String> keys = new TreeSet<>();
        Set<String> keys0 = exMap.keySet();
        String str = new String();
        Iterator iterator = keys0.iterator();
        while (iterator.hasNext()){
            str = iterator.next().toString();
            if (prefix.length() <= str.length())
                if (str.substring(0, prefix.length()).equals(prefix)) keys.add(str);
        }
        return keys;
    }
}
