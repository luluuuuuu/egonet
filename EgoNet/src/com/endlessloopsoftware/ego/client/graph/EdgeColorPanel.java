package com.endlessloopsoftware.ego.client.graph;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.*;
import java.awt.event.*;
import org.jdesktop.layout.GroupLayout;

import org.egonet.util.listbuilder.Selection;
import org.egonet.util.table.*;

import com.endlessloopsoftware.ego.*;
import com.endlessloopsoftware.ego.client.EgoClient;
import com.endlessloopsoftware.ego.client.graph.NodeProperty.NodeShape;

public class EdgeColorPanel extends JPanel {

	private JLabel questionLabel;

	private JComboBox questionCombo;

	ColorChooserEditor colorChooser;

	private ChoosablePropertyTableModel tableModel;

	private JTable table;

	private GroupLayout layout;

	private GraphRenderer graphRenderer;

	private GraphData graphData;

	private JButton applyButton;

	public static boolean[][] edgesSelected;

	List<Selection> selectionList = new ArrayList<Selection>();

	List<Question> qList = new ArrayList<Question>();

	public EdgeColorPanel(GraphRenderer renderer) {
		this.graphRenderer = renderer;
		layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutocreateGaps(true);
		layout.setAutocreateContainerGaps(true);
		graphData = new GraphData();
		createComponents();
	}

	private void createComponents() {

		questionLabel = new JLabel("Choose question to shape");
		questionLabel.setVisible(true);

		// create questionCombo

		Study study = EgoClient.interview.getStudy();
		QuestionList questionList = study.getQuestions();
		Map<Long, Question> questionMap = questionList.getQuestionMap();
		for (Long key : questionMap.keySet()) {
			Question currentQuestion = questionMap.get(key);
			int questionType = currentQuestion.questionType;
			if (questionType == Question.ALTER_PAIR_QUESTION) {
				// populate the list box with only questions that have choices
				// as answers
				if (currentQuestion.selections.length >= 1)
					qList.add(currentQuestion);
			}
		}
		questionCombo = new JComboBox(qList.toArray());
		questionCombo.setVisible(true);
		questionCombo.setEnabled(true);
		questionCombo.setPreferredSize(new Dimension(20, 20));
		questionCombo.setMaximumSize(new Dimension(20, 30));
		questionCombo.setAutoscrolls(true);

		questionCombo.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					createTable();
					drawPanel();
				}
			}
		});

		// create apply button
		applyButton = new JButton("Apply Colors");
		applyButton.setVisible(true);
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEdgeColor();
			}
		});

		Question question = (Question) questionCombo.getSelectedItem();
		edgesSelected = new boolean[qList.size()][question.selections.length];
		for (int i = 0; i < qList.size(); i++) {
			for (int j = 0; j < question.selections.length; j++) {
				edgesSelected[i][j] = false;
			}
		}

		createTable();
		drawPanel();

	}

	private void createTable() {

		Question question = (Question) questionCombo.getSelectedItem();
		int category = Question.ALTER_PAIR_QUESTION;
		int noOfRows = question.selections.length;
		Object[][] rowData = new Object[noOfRows][3];
		/* change the list of selections based on the selected question */
		if (!selectionList.isEmpty()) {
			selectionList.removeAll(selectionList);
		}
		for (Selection selection : question.selections) {
			selectionList.add(selection);
		}
		// populate the responses
		for (int i = 0; i < noOfRows; i++) {
			rowData[i][0] = Boolean.FALSE;
			rowData[i][1] = selectionList.get(i);
			String str = ((Selection) rowData[i][1]).getString();
		}
		// populate the colors
		int noOfColors = question.selections.length;
		Random rand = new Random();
		for (int i = 0; i < noOfColors; i++) {
			int red = rand.nextInt(255);
			int green = rand.nextInt(255);
			int blue = rand.nextInt(255);
			Color color = new Color(red, green, blue);
			rowData[i][2] = color;
		}

		table = new JTable(new ChoosablePropertyTableModel(rowData));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setRowHeight(25);
		table.setVisible(true);
		table.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createLineBorder(Color.black), getBorder()));

		TableColumnModel columnModel = table.getColumnModel();
		LabelRenderer selectionRenderer = new LabelRenderer();
		columnModel.getColumn(1).setCellRenderer(selectionRenderer);

		TableCellEditor colorEditor = new ColorEditor();
		columnModel.getColumn(2).setCellEditor(colorEditor);
		ColorRenderer colorButtonRenderer = new ColorRenderer(true);
		columnModel.getColumn(2).setCellRenderer(colorButtonRenderer);

		columnModel.getColumn(0).setMaxWidth(50);
		columnModel.getColumn(1).setMaxWidth(200);
		columnModel.getColumn(2).setMaxWidth(150);
	}

	private void updateEdgeColor() {

		Question question = (Question) questionCombo.getSelectedItem();

		int selectedQuestionIndex = qList.indexOf(question);
		System.out.println("ColorePanel:SelectedQuestionIndex:"
				+ selectedQuestionIndex + " " + question.toString());
		for (int i = 0; i < question.selections.length; i++) {
			Selection selection = question.selections[i];
			GraphQuestion graphQuestion = new GraphQuestion(question,
					selection, Question.ALTER_PAIR_QUESTION);

			if (((Boolean) table.getValueAt(i, 0)) == true) {
				EdgeProperty edgeProperty = new EdgeProperty();
				edgeProperty.setProperty(EdgeProperty.Property.Color);
				edgeProperty.setColor((Color) table.getValueAt(i, 2));
				edgeProperty.setVisible(true);
				edgesSelected[selectedQuestionIndex][i] = true;
				graphRenderer.addQAsettings(graphQuestion, edgeProperty);
				graphRenderer.updateGraphSettings();
			} else {
				edgesSelected[selectedQuestionIndex][i] = false;
				if (EdgeShapePanel.edgesSelected[selectedQuestionIndex][i] == false
						&& EdgeSizePanel.edgesSelected[selectedQuestionIndex][i] == false) {
					EdgeProperty edgeProperty = new EdgeProperty();
					edgeProperty.setProperty(EdgeProperty.Property.Color);
					//edgeProperty.setColor((Color) table.getValueAt(i, 2));
					edgeProperty.setVisible(false);
					graphRenderer.addQAsettings(graphQuestion, edgeProperty);
					graphRenderer.updateGraphSettings();
				}
			}
		}
	}

	private void drawPanel() {
		this.removeAll();

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.add(layout.createParallelGroup().add(questionLabel).add(
				questionCombo).add(table.getTableHeader()).add(table).add(
				applyButton));

		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.add(layout.createParallelGroup(GroupLayout.BASELINE).add(
				questionLabel));
		vGroup.add(layout.createParallelGroup(GroupLayout.BASELINE).add(
				questionCombo));
		vGroup.add(layout.createParallelGroup(GroupLayout.BASELINE).add(
				table.getTableHeader()));
		vGroup.add(layout.createParallelGroup(GroupLayout.BASELINE).add(table));
		vGroup.add(layout.createParallelGroup(GroupLayout.BASELINE).add(
				applyButton));

		layout.setVerticalGroup(vGroup);
	}
}
