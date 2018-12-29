/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author snow
 */
public class DAOException extends Exception{
    public DAOException(String texto){
        super(texto);
    }
    
    public DAOException(Exception e){
        super(e);
    }
    
    public DAOException(String texto,Exception e){
        super(texto, e);
    }
}
