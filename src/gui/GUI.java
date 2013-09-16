package gui;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import listener.CancelButtonListener;
import listener.DefaultTextFieldListener;
import listener.ResultsModel;
import backend.*;

/*
 * NewJFrame.java
 *
 * Created on Jun 24, 2011, 7:35:25 PM
 */
/**
 *
 * @author Ross Kahn
 */
public class GUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
    private javax.swing.JDialog New_Ingredient_Dialog;
    private javax.swing.JDialog New_Recipe_Dialog;
    private javax.swing.ButtonGroup buttonGroup1;
    //private javax.swing.JTextArea descriptionArea;
    private JTextPane descriptionArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel resultSelectionLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField newRecipeSearchField;
    private javax.swing.JTextField newIngredientNameField;
    private javax.swing.JButton newIngredientCancel;
    private javax.swing.JMenuItem newIngredientMenu;
    private javax.swing.JTextPane newIngredientNotes;
    private javax.swing.JButton newIngredientSubmit;
    private javax.swing.JButton newRecipeCancel;
    private javax.swing.JList newRecipeIngredientsList;
    private javax.swing.JMenuItem newRecipeMenu;
    private javax.swing.JTextField newRecipeNameField;
    private javax.swing.JTextPane newRecipeNotes;
    private javax.swing.JButton newRecipeSubmit;
    private javax.swing.JPanel newRecipeTablePanel;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JList resultList;
    private javax.swing.JTextField searchField;
    private javax.swing.JList searchList;
    private javax.swing.JRadioButton toggleIngredients;
    private javax.swing.JRadioButton toggleRecipes;
    private ListSelectionModel searchListSelectionModel;
    private MVController mvc;
    private JScrollPane jScrollPane7;
    private JTable newRecipeTable;
    private NewRecipeTableModel tableModel;
    
	
    /** Creates new form NewJFrame */
    public GUI(MVController mvc) {
    	this.mvc = mvc;
        initComponents();
        customizeComponents();
    }
    
    private void initComponents() {

    	// JButtonGroup
        buttonGroup1 = new javax.swing.ButtonGroup();

        // RadioButton
        toggleIngredients = new javax.swing.JRadioButton();
        toggleRecipes = new javax.swing.JRadioButton();
        
        // JDialog
        New_Recipe_Dialog = new javax.swing.JDialog();
        New_Ingredient_Dialog = new javax.swing.JDialog();
        
        // JPanel
        newRecipeTablePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        
        // JButton
        newRecipeSubmit = new javax.swing.JButton();
        newRecipeCancel = new javax.swing.JButton();
        newIngredientSubmit = new javax.swing.JButton();
        newIngredientCancel = new javax.swing.JButton();
        
        // JScrollPane
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane7 = new JScrollPane();
        
        // JTable
        newRecipeTable = new JTable();
        
        // JTextPane
        newRecipeNotes = new javax.swing.JTextPane();
        newIngredientNotes = new javax.swing.JTextPane();
        
        // JLabel
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        resultSelectionLabel = new javax.swing.JLabel();
        
        // JTextField
        newRecipeSearchField = new javax.swing.JTextField();
        newIngredientNameField = new javax.swing.JTextField();
        newRecipeNameField = new javax.swing.JTextField();
        searchField = new javax.swing.JTextField();
        descriptionArea = new javax.swing.JTextPane();

        // JList
        resultList = new javax.swing.JList();
        newRecipeIngredientsList = new javax.swing.JList();
        searchList = new javax.swing.JList();
        
        // JMenuBar
        jMenuBar1 = new javax.swing.JMenuBar();
        
        // JMenu
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        
        //	JMenuItem
        newRecipeMenu = new javax.swing.JMenuItem();
        newIngredientMenu = new javax.swing.JMenuItem();
        // ################################################3
        
        
    }
    
    private void customizeComponents(){
    	customizeNewRecipeWindow();
    	customizeNewIngredientWindow();	
        customizeDescriptionPanel();
        customizeSearchPanel();
        customizeResultPanel();
        customizeMenuBar();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Midas Magic Helper");
        
        doLayouts();
        populateSearchList();
        pack();
    }
    
    private void customizeSearchPanel(){
    	jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Search:");

        searchField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchField.addKeyListener(new SearchFieldListener());

        searchList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchListSelectionModel = searchList.getSelectionModel();
        searchListSelectionModel.addListSelectionListener(new SearchListSelectionListener());
        
        jScrollPane1.setViewportView(searchList);
        
        ToggleViewTypeListener toggler = new ToggleViewTypeListener();
        buttonGroup1.add(toggleIngredients);
        toggleIngredients.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleIngredients.setSelected(true);
        toggleIngredients.setText("Ingredients");
        toggleIngredients.addActionListener(toggler);

        buttonGroup1.add(toggleRecipes);
        toggleRecipes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleRecipes.setText("Recipes");
        toggleRecipes.addActionListener(toggler);
        //toggleRecipes.setSelected(false);
    }
    
    private void customizeResultPanel(){
    	jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        resultLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resultLabel.setText("Recipes For:");

        resultSelectionLabel.setBackground(new java.awt.Color(255, 51, 0));
        resultSelectionLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        resultList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        
        resultList.setListData(new String[]{"<html><em>Select an ingredient</em></html>"});
        ListSelectionModel lsm = resultList.getSelectionModel();
        lsm.addListSelectionListener(new ResultListSelectionListener());
        
        resultList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(resultList);

        resultLabel.getAccessibleContext().setAccessibleName("");
    }
    
    private void customizeDescriptionPanel(){
    	jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descriptionArea.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        descriptionArea.setEditable(false);
        jScrollPane3.setViewportView(descriptionArea);
        
        descriptionArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Ingredient Information"));
    }
    
    private void customizeMenuBar(){
    	jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        newRecipeMenu.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        
        newIngredientMenu.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        
        newRecipeMenu.setText("New Recipe");
        newRecipeMenu.addActionListener(new MenuActionListener(New_Recipe_Dialog));
        jMenu2.add(newRecipeMenu);

        newIngredientMenu.setText("New Ingredient");
        newIngredientMenu.addActionListener(new MenuActionListener(New_Ingredient_Dialog));
        jMenu2.add(newIngredientMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);
    }
    
    private void customizeNewIngredientWindow(){
    	newIngredientNotes.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingredient Notes (Optional)"));
        jScrollPane6.setViewportView(newIngredientNotes);

        newIngredientNameField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        newIngredientNameField.setText("New Ingredient");

        newIngredientSubmit.setText("Submit");

        newIngredientCancel.setText("Cancel");
        
        newIngredientSubmit.addActionListener(new IngredientSubmitButtonListener());
        
        newIngredientCancel.addActionListener(new CancelButtonListener(New_Ingredient_Dialog));
    }
    
    private void customizeNewRecipeWindow(){
    	New_Recipe_Dialog.setTitle("New Recipe");
        New_Recipe_Dialog.setAlwaysOnTop(true);
        New_Recipe_Dialog.setModal(true);
        New_Recipe_Dialog.setSize(900, 650);
        New_Recipe_Dialog.setResizable(false);
        
        New_Ingredient_Dialog.setTitle("New Ingredient");
        New_Ingredient_Dialog.setAlwaysOnTop(true);
        New_Ingredient_Dialog.setModal(true);
        New_Ingredient_Dialog.setSize(500,300);
        New_Ingredient_Dialog.setResizable(false);

        newRecipeTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        newRecipeTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Possible Ingredients"));
                
        JComboBox combo = new JComboBox();
        for(int i=0; i<50; i++){
        	combo.addItem(i);
        }
        
        tableModel = new NewRecipeTableModel(mvc, newRecipeTable, this);
        newRecipeTable.setModel(tableModel);
        newRecipeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        /*EventListener tableListener = new TableSelectionListener();
        newRecipeTable.addMouseListener((MouseListener)tableListener);*/
      
        jScrollPane7.setViewportView(newRecipeTable);

        newRecipeSubmit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        newRecipeSubmit.setText("Submit");
        newRecipeSubmit.addActionListener(new RecipeSubmitButtonListener());

        newRecipeIngredientsList.setFont(new Font("Tahoma", 0, 18));
        newRecipeIngredientsList.setBackground(new java.awt.Color(236, 233, 216));
        newRecipeIngredientsList.setBorder(javax.swing.BorderFactory.createTitledBorder("Added Ingredients"));
        jScrollPane4.setViewportView(newRecipeIngredientsList);

        newRecipeNameField.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        newRecipeNameField.setText("New Recipe");

        newRecipeNotes.setBorder(javax.swing.BorderFactory.createTitledBorder("Recipe Notes"));
        newRecipeNotes.setToolTipText("Add recipe notes here");
        jScrollPane5.setViewportView(newRecipeNotes);

        newRecipeCancel.setText("Cancel");
        newRecipeCancel.addActionListener(new CancelButtonListener(New_Recipe_Dialog));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Search:");

        newRecipeSearchField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        newRecipeSearchField.addKeyListener(new RecipeSearchListener());
    }
    
    
    private void doLayouts(){
    	javax.swing.GroupLayout newRecipeTableLayout = new javax.swing.GroupLayout(newRecipeTablePanel);
        newRecipeTablePanel.setLayout(newRecipeTableLayout);
        newRecipeTableLayout.setHorizontalGroup(
            newRecipeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );
        newRecipeTableLayout.setVerticalGroup(
            newRecipeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newRecipeTableLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout New_Recipe_DialogLayout = new javax.swing.GroupLayout(New_Recipe_Dialog.getContentPane());
        New_Recipe_Dialog.getContentPane().setLayout(New_Recipe_DialogLayout);
        New_Recipe_DialogLayout.setHorizontalGroup(
            New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, New_Recipe_DialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                                .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(newRecipeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(newRecipeSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
                            .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                                .addComponent(newRecipeTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))))
                    .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(newRecipeCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
                        .addComponent(newRecipeSubmit)))
                .addGap(21, 21, 21))
        );
        New_Recipe_DialogLayout.setVerticalGroup(
            New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(New_Recipe_DialogLayout.createSequentialGroup()
                        .addComponent(newRecipeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(newRecipeSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(newRecipeTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(New_Recipe_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newRecipeSubmit)
                    .addComponent(newRecipeCancel))
                .addContainerGap())
        );
        javax.swing.GroupLayout New_Ingredient_DialogLayout = new javax.swing.GroupLayout(New_Ingredient_Dialog.getContentPane());
        New_Ingredient_Dialog.getContentPane().setLayout(New_Ingredient_DialogLayout);
        New_Ingredient_DialogLayout.setHorizontalGroup(
            New_Ingredient_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(New_Ingredient_DialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(New_Ingredient_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newIngredientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, New_Ingredient_DialogLayout.createSequentialGroup()
                        .addComponent(newIngredientCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                        .addComponent(newIngredientSubmit)))
                .addContainerGap())
        );
        New_Ingredient_DialogLayout.setVerticalGroup(
            New_Ingredient_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(New_Ingredient_DialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newIngredientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_Ingredient_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientSubmit)
                    .addComponent(newIngredientCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(toggleIngredients)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toggleRecipes)
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleIngredients)
                    .addComponent(toggleRecipes))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(resultLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resultSelectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultSelectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void populateSearchList(){
    	searchList.setModel(new ResultsModel(mvc.getCurrentFilteredData(), toggleIngredients.isSelected()));
    }
    
    private void populateResultsList(Ingredient i){
    	ArrayList<String> results = new ArrayList<String>();
    	results.addAll(i.getPrintableParentRecipes());
    	resultList.setModel(new ResultsModel(results, toggleIngredients.isSelected()));
    	//resultList.addListSelectionListener(new ResultListSelectionListener());
    }
    
    private void populateResultsList(Recipe r){
    	ArrayList<String> results = new ArrayList<String>();
    	results.addAll(r.getPrintableIngredients());
    	resultList.setModel(new ResultsModel(results, toggleIngredients.isSelected()));
    	//resultList.addListSelectionListener(new ResultListSelectionListener());
    }
    
    public void populateAddedIngredientsList(){
    	if(tableModel.getSelectedIngredients().size() == 0){
    		newRecipeIngredientsList.setListData(new String[]{"<html><em>Select ingredients to " +
			"add to recipe</em></html>"});
    	}else{
    		newRecipeIngredientsList.setListData(tableModel.getSelectedIngredients().toArray());
    	}
    	
    }
    
    public void resetMenuWindows(){
		newIngredientNameField.setText("New Ingredient");
		
		newIngredientNotes.setText("");
		
		EventListener ingEL = new DefaultTextFieldListener(newIngredientNameField);
        newIngredientNameField.addMouseListener((MouseListener) ingEL);
		newIngredientNameField.addKeyListener((KeyListener) ingEL);
		
		newRecipeIngredientsList.setListData(new String[]{"<html><em>Select ingredients to " +
				"add to recipe</em></html>"});
		newRecipeNameField.setText("New Recipe");
		
		EventListener recEL = new DefaultTextFieldListener(newRecipeNameField);
		newRecipeNameField.addMouseListener((MouseListener) recEL);
		newRecipeNameField.addKeyListener((KeyListener) recEL);
		
		newRecipeNotes.setText("");
		newRecipeSearchField.setText("");
	
		mvc.resetData();
		tableModel = new NewRecipeTableModel(mvc, newRecipeTable, this);
		newRecipeTable.setModel(tableModel);
	}
    
    public void populateDescription(String info){
    	if("".equals(info)){
    		descriptionArea.setText("No Information Available");
    	}else if(info == null){
    		descriptionArea.setText("");
    		if(toggleIngredients.isSelected()){
    	        descriptionArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Ingredient Information"));
    		}else{
    	        descriptionArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Recipe Information"));
    		}
    	}else{
    		descriptionArea.setText(info);
    	}
    }
    
    public void resetSearchFilter(){
    	searchField.setText("");
    	mvc.resetData();
    	populateSearchList();
    }
    
    // ######################### INSIDE CLASSES #########################################
    
    class RecipeSearchListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			tableModel.logFilter(arg0.getKeyChar());
		}
    	
    }
    
    class IngredientSubmitButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			submit();
		}
		
		public void submit(){
			String ingredientName = newIngredientNameField.getText();
			if(Ingredient.allIngredients.containsKey(ingredientName)){
				throwMessage("An Ingredient called " + ingredientName + "already exists.");
			}else if("".equals(ingredientName)){
				throwMessage("This Ingredient needs a name.");
				return;
			}
			
			Ingredient i = new Ingredient(ingredientName);
			i.setInfo(newIngredientNotes.getText());
			mvc.resetData();
			New_Ingredient_Dialog.setVisible(false);
			populateSearchList();
			
			mvc.writeData(i);
		}
		
		public void throwMessage(String message){
			JOptionPane.showMessageDialog(New_Ingredient_Dialog, message, "Error!", 
					JOptionPane.ERROR_MESSAGE);
		}    	
    }
    
    class SearchFieldListener implements KeyListener{
    	
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
			
			mvc.logFilter(arg0.getKeyChar());
			if(mvc.getFilter().isEmpty()){
				resetSearchFilter();
			}

			populateSearchList();
		}
    	
    }
    
    class RecipeSubmitButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
						
			String recipeName = newRecipeNameField.getText();

			if(Recipe.allRecipes.containsKey(recipeName)){
				throwMessage("A Recipe with the name " + recipeName + " already exists.");
				return;
			}else if("".equals(recipeName)){
				throwMessage("This recipe needs a name!");
				return;
			}
			
			ArrayList<IngredientQuantity> ingredients = tableModel.getSelectedIngredients();
			if(ingredients.size() < 1){
				throwMessage("Recipe must have at least 1 ingredient.");
				return;
			}
			
			Recipe recipe = new Recipe(recipeName);
			String recipeNotes = newRecipeNotes.getText();
			
			recipe.addIngredients(ingredients);
			recipe.setInfo(recipeNotes);
			
			mvc.resetData();
			New_Recipe_Dialog.setVisible(false);
			populateSearchList();
			
			mvc.writeData(recipe);
		}	
		
		public void throwMessage(String message){
			JOptionPane.showMessageDialog(New_Recipe_Dialog, message, "Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
    }
    
    class ResultListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
    		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    		if(lsm.getMinSelectionIndex() >= 0){
    			Item i;
    			if(toggleIngredients.isSelected()){
    				i = Recipe.allRecipes.get(resultList.getModel().getElementAt(
    						lsm.getMinSelectionIndex()));
    			}else{
    				i = Ingredient.allIngredients.get(resultList.getModel().getElementAt(
    						lsm.getMinSelectionIndex()));

    			}
    			if(i == null){
    				descriptionArea.setText("No information available");
    			}else{
    				descriptionArea.setText(i.getName()+":\n"+ i.getInfo());
    				if(i instanceof Recipe){
    					Recipe r = (Recipe) i;
    					descriptionArea.setText(descriptionArea.getText() + "\n" +
    							r.getPrintableIngredients());
    				}
    			}
    		}
		}
    	
    }
    
    class SearchListSelectionListener implements ListSelectionListener{
    	
    	@Override
    	public void valueChanged(ListSelectionEvent e) {
    		// TODO Auto-generated method stub
    		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    		if(lsm.getMinSelectionIndex() >= 0){
    			if(toggleIngredients.isSelected()){
    				Ingredient i = mvc.getSelectedIngredient(lsm.getMinSelectionIndex());
    				populateResultsList(i);
    				resultSelectionLabel.setText(i.getName());
    				populateDescription(i.getInfo());
    			}else{
    				Recipe r = mvc.getSelectedRecipe(lsm.getMinSelectionIndex());
    				populateResultsList(r);
    				resultSelectionLabel.setText(r.getName());
    				populateDescription(r.getInfo());
    			}
    		}
    	}
    }
    
    class ToggleViewTypeListener implements ActionListener{

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    		if(toggleIngredients.isSelected()){
    			resultList.setListData(new String[]{"<html><em>Select an ingredient</em></html>"});
    			resultLabel.setText("Recipe for:");
    			mvc.setActiveData(MVController.INGREDIENT);
    		}else{
    			resultList.setListData(new String[]{"<html><em>Select a recipe</em></html>"});
    			resultLabel.setText("Ingredients in:");
    			mvc.setActiveData(MVController.RECIPE);
    		}
    		resetSearchFilter();
    		populateSearchList();
    		populateDescription(null);
    	}
    }
    
    class MenuActionListener implements ActionListener{

    	JDialog menu;
    	
    	public MenuActionListener(JDialog menu){
    		this.menu = menu;
    	}
    	
    	public MenuActionListener(){
    		
    	}
    	
    	private void openMenu(JDialog window){
    		resetMenuWindows();
    		window.setVisible(true);
    	}
    	
    	@Override
    	public void actionPerformed(ActionEvent arg0) {
    		// TODO Auto-generated method stub
    		openMenu(menu);
    	}
    }
}
