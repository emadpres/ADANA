package com.reveal.asia;

import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * Created by emadpres on 7/25/17.
 */
public class GenerateDialog extends DialogWrapper
{
    private CollectionListModel<PsiField> myFields;
    private LabeledComponent<JPanel> myComponent;
    protected GenerateDialog(PsiClass psiClass)
    {
        super(psiClass.getProject());

        setTitle("Select Threshold");

        myFields = new CollectionListModel<PsiField>(psiClass.getAllFields());
        JList fieldList = new JList(myFields);
        fieldList.setCellRenderer(new DefaultPsiElementCellRenderer());
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(fieldList);
        decorator.disableAddAction();
        JPanel panel = decorator.createPanel();
        myComponent = LabeledComponent.create(panel, "Select:");

        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel()
    {
        return myComponent;
    }

    public List<PsiField> getFields()
    {
        return myFields.getItems();
    }
}
