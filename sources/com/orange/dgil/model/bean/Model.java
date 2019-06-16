package com.orange.dgil.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {
    private static final long serialVersionUID = 1;
    private long id;
    private final List<List<Point>> strokes;

    public Model() {
        this.strokes = new ArrayList();
    }

    public Model(long id, List<List<Point>> strokes) {
        this.id = id;
        this.strokes = strokes;
    }

    public void resetAllStrokes() {
        this.strokes.clear();
    }

    public void newStroke() {
        this.strokes.add(new ArrayList());
    }

    public void addPoint(int x, int y) {
        ((List) this.strokes.get(this.strokes.size() - 1)).add(new Point(x, y));
    }

    public int[][] toDgilArray() {
        int[][] intArray = new int[this.strokes.size()][];
        for (int i = 0; i < this.strokes.size(); i++) {
            intArray[i] = new int[(((List) this.strokes.get(i)).size() * 2)];
            for (int j = 0; j < ((List) this.strokes.get(i)).size(); j++) {
                intArray[i][j * 2] = ((Point) ((List) this.strokes.get(i)).get(j)).getX();
                intArray[i][(j * 2) + 1] = ((Point) ((List) this.strokes.get(i)).get(j)).getY();
            }
        }
        return intArray;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<List<Point>> getStrokes() {
        return this.strokes;
    }
}
