package org.sean.review.designpattern.singleton;

import java.io.*;

public abstract class BaseSingletonTest<T> {

    protected void writeInstance(String fileName, Object instance) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(instance);
    }

    protected T readInstance(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        return (T) ois.readObject();
    }

    protected void clearPersistence(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

}
