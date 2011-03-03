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
                view.loadContent();
                view.showViewing();
                return new Viewing();
            }
            else
            {
                if(view.displayCreateNewWordDialog(word))
                {
                    word.save("");
                    view.setCurrentWikiWordPage(word);
                    return new Viewing();
                }
                else if(view.setCurrentWikiWordPageFromHistory(-1))
                {
                    return this;
                }
                else
                {
                    return transitionExit(null);
                }
            }
        }

        @Override
        public State transitionEdit(WikiWordPageBase word) throws Exception {
            if(word.exists())
            {
                view.loadContent();
                view.showEditing();
                return new Editing();
            }
            else
            {
                return transitionExit(null);
            }
        }

        @Override
        public State transitionCancel(WikiWordPageBase word) throws Exception {
            return this;
        }

        @Override
        public State transitionSave(WikiWordPageBase word) throws Exception {
            return this;
        }

        @Override
        public State transitionPreview(WikiWordPageBase word) throws Exception {
            return this;
        }

        @Override
        public State transitionExit(WikiWordPageBase word) throws Exception {
            PjWikiApp.getApplication().exit();
            return null;
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
            return this;
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
            view.showPreviewing();
            return new Previewing();
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
            view.showEditing();
            return new Editing();
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
            return this;
        }

        @Override
        public State transitionExit(WikiWordPageBase word) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
