package il.org.spartan.spartanizer.utils.tdd;

import org.eclipse.jdt.core.dom.*;

/** @author Ori Marcovitch
 * @since Oct 31, 2016 */
public enum determineIf {
  ;
  
  /** see issue #718 for more details
   * @author Amir Sagiv
   * @author Oren Afek
   * @since 16-11-02
   * @param ¢
   * @return true iff the method have at least 3 parameters and defines more than 5 variables */
  public static boolean loaded(@SuppressWarnings("unused") final MethodDeclaration ¢) {
    return ¢ != null && !"g".equals((¢.getName() + "")) && ¢.parameters().size() >= 3;
  }
  
  // For you to implement! Let's TDD and get it on!
  /** see issue #716 for more details
   * @author Ron Gatenio
   * @author Roy Shchory
   * @since 16-11-02
   * @param d
   * @return true iff the method has at least 10 statements */
  public static boolean hasManyStatements(@SuppressWarnings("unused") final MethodDeclaration __) {
    return true;
  }
  
  
  /** see issue #714 for more details
   * @author Arthur Sapozhnikov
   * @author Assaf Lustig
   * @author Dan Abramovich
   * @since 16-11-02
   * @param m
   * @return true iff the class contains only final fields */
  public static boolean isImmutable(@SuppressWarnings("unused") final TypeDeclaration m) {
    return true;
  }
  // For you to implement! Let's TDD and get it on!
}
