package il.org.spartan.plugin;

import org.eclipse.core.commands.*;

import il.org.spartan.spartanizer.dispatch.*;

/** A handler for {@link Spartanizations} This handler executes all safe
 * spartanizations on all Java files in the current project, while exposing
 * static methods to spartanize only specific compilation units.
 * @author Ofir Elmakias <code><elmakias [at] outlook.com></code>
 * @since 2015/08/01 */
public final class SpartanizeCurrent extends BaseHandler {
  /** Instantiates this class */
  public SpartanizeCurrent() {
    super(null);
  }

  @Override public Void execute(@SuppressWarnings("unused") final ExecutionEvent __) {
    new Trimmer().apply(eclipse.currentCompilationUnit(), eclipse.selectedText());
    return null;
  }
}
