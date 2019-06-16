package com.orange.dgil.model.dao;

import com.orange.dgil.model.bean.Learning;
import com.orange.dgil.model.bean.Model;
import com.orange.dgil.model.bean.Symbol;
import java.io.IOException;
import java.util.List;

public interface ILearningDao {
    Learning addLearning(Learning learning) throws IOException;

    Model addModel(Model model, Symbol symbol) throws IOException;

    Symbol addSymbol(Symbol symbol, Learning learning) throws IOException;

    List<Learning> getLearnings();

    List<Model> getModels(Symbol symbol);

    List<Symbol> getSymbols(Learning learning);

    void removeLearning(Learning learning) throws IOException;

    void removeModel(Model model, Symbol symbol) throws IOException;

    void removeSymbol(Symbol symbol, Learning learning) throws IOException;

    void updateSymbol(Symbol symbol);
}
