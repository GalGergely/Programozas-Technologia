/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author pbali
 */
public class HighScore {
    public final String name;
    public final int score;
    
    public HighScore(String name,int score)
    {
        this.name=name;
        this.score=score;
    }
    
    public int getscore()
    {
        return score;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
        {
            return true;
        }
        if(obj==null)
        {
            return false;
        }
        if(getClass()!=obj.getClass())
        {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return name+"-"+score;
    }
}
