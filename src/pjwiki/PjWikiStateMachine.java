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
    public PjWikiStateMachine(PjWikiView view)
    {
        this.view = view;
    }
    
    PjWikiView view;
    State currentState = new Viewing();
    
    public State transitionNavigate(WikiWordPageBase word) throws Exception
        { return currentState.transitionNavigate(word); }
    public State transitionEdit(WikiWordPageBase word) throws Exception
        { return currentState.transitionEdit(word);  }
    public State transitionCancel(WikiWordPageBase word) throws Exception
        { return currentState.transitionCancel(word);  }
    public State transitionSave(WikiWordPageBase word) throws Exception
        { return currentState.transitionSave(word);  }
    public State transitionPreview(WikiWordPageBase word) throws Exception
        { return currentState.transitionPreview(word);  }
    public State transitionExit(WikiWordPageBase word) throws Exception
        { return currentState.transitionExit(word);  }
    
    public abstract class State
    {
        public abstract State transitionNavigate(WikiWordPageBase word) throws Exception;
        public abstract State transitionEdit(WikiWordPageBase word) throws Exception;
        public abstract State transitionCancel(WikiWordPageBase word) throws Exception;
        public abstract State transitionSave(WikiWordPageBase word) throws Exception;
        public abstract State transitionPreview(WikiWordPageBase word) throws Exception;
        public abstract State transitionExit(WikiWordPageBase word) throws Exception;
    }
    
    public class Viewing extends State
    {
        public Viewing()
        {
            
        }

        @Override
        public State transitionNavigate(WikiWordPageBase word) throws Exception {
            if(word.exists())
            {
                view.setCurrentWikiWordPage(word);
                view.showViewing();
                return this;
            }
            else
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        }

        @Override
        public State transitionEdit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionCancel(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionSave(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionPreview(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionExit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
    
    public class Editing extends State
    {
        public Editing()
        {
        
        }

        @Override
        public State transitionNavigate(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionEdit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionCancel(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionSave(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionPreview(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionExit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    public class Previewing extends State
    {
        public Previewing()
        {
            
        }

        @Override
        public State transitionNavigate(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionEdit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionCancel(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionSave(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionPreview(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public State transitionExit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
