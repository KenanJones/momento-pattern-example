package edu.jalc.momentopattern.logging.observers;

import edu.jalc.momentopattern.logging.Message;
import edu.jalc.momentopattern.logging.State;
import edu.jalc.momentopattern.logging.messages.*;
import edu.jalc.momentopattern.logging.observers.utils.StateLookUpTable;

public class LogBeeper implements Observer{

  private State rootLevel;

  public LogBeeper(){

    switch(System.getProperty("log root level")){
      case "DEBUG": rootLevel = State.DEBUG; break;
      case "TRACE": rootLevel = State.TRACE; break;
      case "WARN": rootLevel = State.WARN; break;
      case "ERROR": rootLevel = State.ERROR; break;
      case "FATAL": rootLevel = State.FATAL; break;
      case "INFO":
      default: rootLevel = State.INFO;
    }

  }
  public void observe(Message message){
    try{
      State level = StateLookUpTable.values.get(message.getClass());
  		if(level.ordinal() >= rootLevel.ordinal()){
        System.out.print("\7");
        Thread.sleep(1000);
        System.out.print("\7");
        //java.awt.Toolkit.getDefaultToolkit().beep();
  		}
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
