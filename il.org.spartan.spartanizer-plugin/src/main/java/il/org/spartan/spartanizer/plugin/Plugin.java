package il.org.spartan.spartanizer.plugin;

import static il.org.spartan.plugin.old.eclipse.*;
import static il.org.spartan.plugin.preferences.revision.PreferencesResources.*;
import static il.org.spartan.plugin.preferences.revision.XMLSpartan.*;

import java.util.concurrent.atomic.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.*;
import org.eclipse.ui.*;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.*;
import org.w3c.dom.*;

import fluent.ly.*;
import il.org.spartan.athenizer.*;
import il.org.spartan.plugin.old.*;
import il.org.spartan.plugin.preferences.revision.*;
import il.org.spartan.spartanizer.plugin.widget.*;
import il.org.spartan.spartanizer.research.analyses.*;

/** TODO Artium Nihamkin please add a description
 * @author Artium Nihamkin
 * @author Ofir Elmakias
 * @author Ori Roth
 * @since 2013/01/01
 * @since 2015/09/06 (Updated - auto initialization of the plugin)
 * @since 2.6 (Updated - apply nature to newly opened projects) */
public final class Plugin extends AbstractUIPlugin implements IStartup {
  public static final String ID = "il.org.spartan.plugin";
  private static final String NEW_PROJECT = "new_project";
  private static Plugin plugin;
  private static boolean listening;
  private static final int SAFETY_DELAY = 100;
  private static final AtomicBoolean earlyStartupGuard = new AtomicBoolean(true);

  public static AbstractUIPlugin plugin() {
    return plugin;
  }
  private static void startSpartan() {
    RefreshAll.go();
  }
  public Plugin() {
    plugin = this;
  }
  /** Called whenever the plugin is first loaded into the workbench */
  @Override public void earlyStartup() {
    if (earlyStartupGuard.getAndSet(false) && ZOOMER_AUTO_ACTIVISION_VALUE.get())
      InflateHandler.goWheelAction();
  }
  @Override public void start(final BundleContext c) throws Exception {
    super.start(c);
    note.logger.fine("START " + this);
    try {
      startSpartan();
      addPartListener();
      WidgetOperationPoint.load();
      loadPreferences();
    } catch (final IllegalStateException ¢) {
      note.bug(¢);
    }
  }
  @Override public void stop(final BundleContext ¢) throws Exception {
    note.logger.fine("STOP " + this);
    plugin = null;
    super.stop(¢);
  }
  @Override protected void loadDialogSettings() {
    note.logger.finest(this + "");
    super.loadDialogSettings();
  }
  @Override protected void refreshPluginActions() {
    note.logger.finest(this + "");
    super.refreshPluginActions();
  }
  @Override protected void saveDialogSettings() {
    note.logger.finest(this + "");
    super.saveDialogSettings();
  }
  private static void addPartListener() {
    if (listening)
      return;
    final IWorkspace w = ResourcesPlugin.getWorkspace();
    if (w == null)
      return;
    w.addResourceChangeListener(e -> {
      if (e == null || e.getDelta() == null || !PreferencesResources.NEW_PROJECTS_ENABLE_BY_DEFAULT_VALUE.get())
        return;
      try {
        final MProject mp = new MProject();
        e.getDelta().accept(d -> {
          if (d == null || d.getResource() == null || !(d.getResource() instanceof IProject))
            return true;
          final IProject p = (IProject) d.getResource();
          if (d.getKind() != IResourceDelta.ADDED)
            return true;
          mp.p = p;
          mp.type = NEW_PROJECT;
          return true;
        });
        // TODO Ori Roth: please clean this up
        if (mp.p != null)
          Job.createSystem(pm -> {
            try {
              if (mp.type.equals(NEW_PROJECT)) {
                eclipse.addNature(mp.p);
                mp.p.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
              }
            } catch (final Exception ¢) {
              note.bug(¢);
            }
          }).schedule(SAFETY_DELAY);
      } catch (final CoreException ¢) {
        note.bug(¢);
      }
    });
    listening = true;
  }

  /** TODO Ori Roth: not convinced it is required. --yg */
  static class MProject {
    public IProject p;
    public String type;
  }

  /** Load all the relevant prefrences from all resources (incliding the XML
   * file) */
  private static void loadPreferences() {
    robust.ly(() -> {
      NEW_PROJECTS_ENABLE_BY_DEFAULT_VALUE.set(store().getBoolean(NEW_PROJECTS_ENABLE_BY_DEFAULT_ID));
      ZOOMER_REVERT_METHOD_VALUE.set(store().getBoolean(ZOOMER_REVERT_METHOD_ID));
      ZOOMER_AUTO_ACTIVISION_VALUE.set(store().getBoolean(ZOOMER_AUTO_ACTIVISION_ID));
      final Document doc = XMLSpartan.getXML(getAllSpartanizerProjects()[0]);
      doc.getDocumentElement().normalize();
      notation.cent = "cent".equals(doc.getElementsByTagName(NOTATION).item(0).getAttributes().item(1).getNodeValue()) ? "¢"
          : doc.getElementsByTagName(NOTATION).item(0).getAttributes().item(1).getNodeValue();
      notation.return$ = doc.getElementsByTagName(NOTATION).item(1).getAttributes().item(1).getNodeValue();
      WidgetPreferences.setDefaults();
    }, λ -> note.bug(λ));
  }
}
