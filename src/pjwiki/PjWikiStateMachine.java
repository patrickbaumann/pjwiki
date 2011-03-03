/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

/**
 *
 * @author Patrick
 */
public class PjWikiStateMachine
{
    State currentState = new Viewing();
    
    public boolean transition(Transition transition) throws Exception
    {
        try
        {
            currentState.transition(transition);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    
    public class Transition{
        public WikiWord word;
        Transition(WikiWord word)
        {
            this.word = word;
        }
    };
    public class Navigate extends Transition {public Navigate(WikiWord word){super(word);}};
    public class Edit extends Transition {public Edit(WikiWord word){super(word);}};
    public class Cancel extends Transition {public Cancel(WikiWord word){super(word);}};
    public class Save extends Transition {public Save(WikiWord word){super(word);}};
    public class Preview extends Transition {public Preview(WikiWord word){super(word);}};
    public class Exit extends Transition {public Exit(WikiWord word){super(word);}};
    
    private abstract class State
    {
        public abstract State transition(Navigate transition) throws Exception;
        public abstract State transition(Edit transition) throws Exception;
        public abstract State transition(Cancel transition) throws Exception;
        public abstract State transition(Save transition) throws Exception;
        public abstract State transition(Preview transition) throws Exception;
        public abstract State transition(Exit transition) throws Exception;
        public State transition(Transition transition) throws Exception
        {
            if(transition instanceof Navigate)
            {   return transition((Navigate)transition); }
            else if(transition instanceof Edit)
            {   return transition((Edit)transition); }
            else if(transition instanceof Cancel)
            {   return transition((Cancel)transition); }
            else if(transition instanceof Save)
            {   return transition((Save)transition); }
            else if(transition instanceof Preview)
            {   return transition((Preview)transition); }
            else if(transition instanceof Exit)
            {   return transition((Exit)transition); }
            else { throw new Exception("Invalid transition!"); }
        }
    }
    
    public class Viewing extends State
    {

        @Override
        public State transition(Navigate transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Edit transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Cancel transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Save transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Preview transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Exit transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public class Editing extends State
    {

        @Override
        public State transition(Navigate transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Edit transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Cancel transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Save transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Preview transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Exit transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class Previewing extends State
    {

        @Override
        public State transition(Navigate transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Edit transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Cancel transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Save transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Preview transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transition(Exit transition) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}
