package kn.severina.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kn.severina.usermanagement.User;
import kn.severina.usermanagement.db.DatabaseException;
import kn.severina.usermanagement.util.Messages;

public class DeletePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JLabel dateOfBirthLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;
	private User user;

	public DeletePanel(MainFrame frame) {
		parent = frame;
		initialize();
	}

	private void initialize() {
		this.setName("deletePanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		resetFields();
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton());
			buttonPanel.add(getCancelButton());
		}
		return buttonPanel;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setName("okButton"); //$NON-NLS-1$
			okButton.setActionCommand("ok"); //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JPanel getFieldPanel() {
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(4, 2));
			addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameLabel()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameLabel()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthLabel()); //$NON-NLS-1$
			fieldPanel.add(new JLabel(Messages.getString("DeletePanel.accept_question"))); //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JLabel getDateOfBirthLabel() {
		if (dateOfBirthLabel == null) {
			dateOfBirthLabel = new JLabel();
			dateOfBirthLabel.setName("dateOfBirthLabel"); //$NON-NLS-1$
		}
		return dateOfBirthLabel;
	}

	private JLabel getLastNameLabel() {
		if (lastNameLabel == null) {
			lastNameLabel = new JLabel();
			lastNameLabel.setName("lastNameLabel"); //$NON-NLS-1$
		}
		return lastNameLabel;
	}

	private void addLabeledField(JPanel panel, String labelText, JLabel userLabel) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(userLabel);
		panel.add(label);
		panel.add(userLabel);

	}

	private JLabel getFirstNameLabel() {
		if (firstNameLabel == null) {
			firstNameLabel = new JLabel();
			firstNameLabel.setName("firstNameLabel"); //$NON-NLS-1$
		}
		return firstNameLabel;
	}

	
	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) { //$NON-NLS-1$
			try {
				parent.getDao().delete(user);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
		}
		this.setVisible(false);
		parent.showBrowsePanel();
	}

	public void resetFields() {
		this.user = parent.getSelectedUser();
		firstNameLabel.setText(user.getFirstName());
		lastNameLabel.setText(user.getLastName());
		DateFormat formatter = DateFormat.getDateInstance();
		dateOfBirthLabel.setText(formatter.format(user.getDateOfBirthd()));
	}
}