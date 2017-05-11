package il.org.spartan.Leonidas.plugin.leonidas.GenericPsiTypes;

import com.intellij.psi.PsiElement;
import il.org.spartan.Leonidas.PsiTypeHelper;
import il.org.spartan.Leonidas.plugin.leonidas.BasicBlocks.Encapsulator;
import il.org.spartan.Leonidas.plugin.leonidas.BasicBlocks.Statement;


/**
 * @author Amir Sagiv
 * @since 03/05/2017
 */
public class GenericPsiStatementTest extends PsiTypeHelper {

    PsiElement psiElement;
    Statement statement;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        psiElement = createTestStatementFromString("int x;");
        statement = new Statement(psiElement);
    }


    public void testGeneralizes() throws Exception {
        assert statement.generalizes(Encapsulator.buildTreeFromPsi(createTestStatementFromString("x + 1;")));
        assert !statement.generalizes(Encapsulator.buildTreeFromPsi(createTestExpressionFromString("x+1")));
        assert !statement.generalizes(Encapsulator.buildTreeFromPsi(createTestStatementFromString("{int x; int y;}")));
        assert statement.generalizes(Encapsulator.buildTreeFromPsi(createTestStatementFromString("for(;;){}")));
    }


}