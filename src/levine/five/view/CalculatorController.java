package levine.five.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

import application.Main;

public class CalculatorController {

	 //Creates a list of all states to be put into state selection boxes
	ObservableList<String> states = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",  "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",  "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",  "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "U.S. Territory", "Outside of the U.S.");
	
	 //Creates list of numbers for family size selection boxes
	ObservableList<String> numbers = FXCollections.observableArrayList("1", "2", "3", "4+");
	
	 //Creates list of income ranges for parent incomes
	ObservableList<String> money = FXCollections.observableArrayList("None", "Less than $50,000", "$50,000-$99,999", "$100,000-$149,999", "$150,000-$199,999", "$200,000-249,000", "$250,000 or more");
	
	 //Creates list of employment options for a student
	ObservableList<String> employment = FXCollections.observableArrayList("None", "Seasonal employment", "Part-time employment", "Full-time employment");
	
	@FXML //This is the combo box where you select in which state you live	
	private ComboBox<String> homeState = new ComboBox<String>(states);
	
	@FXML //This is the choice box where you select how many people are in your family
	private ComboBox<String> numberOfFamilyMembers = new ComboBox<String>(numbers);
	
	@FXML //This is the choice box where you select how many family members will be in college
	private ComboBox<String> familyInCollege = new ComboBox<String>(numbers);
	
	@FXML //This is the choice box where you select in which the college is
	private ComboBox<String> collegeState = new ComboBox<String>(states);
	
	@FXML //This is where you enter the college name
	private TextField enterUniversity;
	
	@FXML //This is where you enter the college's tuition
	private TextField enterTuition;
	
	@FXML //This is where you select one parent's income
	private ComboBox<String> incomeOne = new ComboBox<String>(money);
	
	@FXML //This is where you select one parent's income
	private ComboBox<String> incomeTwo = new ComboBox<String>(money);
	
	@FXML //This is where you select your income
	private ComboBox<String> incomeStudent = new ComboBox<String>(employment);
	
	@FXML //This is where you enter the tuition you want to pay
	private TextField desiredTuition;
	
	@FXML //This is where the college's tuition will be repeated
	private TextField calcTuition;
	
	@FXML //This is where the total possible aid will be stated
	private TextField calcAid;
	
	@FXML //This is where your total tuition will be stated
	private TextField calcTotal;
	
	@FXML //This is where the difference between what you want to pay and what you'll have to pay is stated
	private TextField calcDifference;
	
	@FXML //This is what you select to calculate aid
	private Button calcButton;
	
	private Main Main;
	
	public CalculatorController() {
		//empty constructor
	}
	
	@FXML
	private void initialize(){	
		
	}
	
	@FXML
	private void fillCombos(){ //Fills combo boxes when mouse is over the border pane
		homeState.setItems(states);
		collegeState.setItems(states);
		
		numberOfFamilyMembers.setItems(numbers);
		familyInCollege.setItems(numbers);
		
		incomeOne.setItems(money);
		incomeTwo.setItems(money);
		
		incomeStudent.setItems(employment);
	}
	
	@FXML
	private void handleCalculate() { //When the calculate button is clicked
		
		double tuition = getTuition();
		calcTuition.setText("$" + tuition);
		
		double aid = calculateAid(tuition);
		calcAid.setText("$" + aid);
		
		double afterAid = calcAfterAid(tuition, aid);
		calcTotal.setText("$" + afterAid);
		
		String difference = calcDifference(afterAid);
		calcDifference.setText(difference);

	}
	
	private double getTuition(){ //Gets tuition
		double tuition = Integer.parseInt(enterTuition.getText());
		double tuition2 = roundTwoDecimals(tuition) +.00;
		return tuition2;
	}
	
	private double calculateAid(double tuition){ //Calculate aid based on selections and data
		double aidPercent = 0.00;
		
		 //Find if in-state student
		if(homeState.getValue() == collegeState.getValue() && homeState.getValue() != null){
			aidPercent += .35;
		}
		
		 //Adjust based on dispersion of income
		if(numberOfFamilyMembers.getValue() == "1"){
			if(familyInCollege.getValue() == "1"){
				aidPercent += .17;
			}
			else{ }
		}
		if(numberOfFamilyMembers.getValue() == "2"){
			if(familyInCollege.getValue() == "1"){
				aidPercent += .14;
			}
			else if(familyInCollege.getValue() == "2"){
				aidPercent += .15;
			}
			else{ }
		}
		if(numberOfFamilyMembers.getValue() == "3"){
			if(familyInCollege.getValue() == "1"){
				aidPercent += .05;
			}
			else if(familyInCollege.getValue() == "2"){
				aidPercent += .11;
			}
			else if(familyInCollege.getValue() == "3"){
				aidPercent += .13;
			}
			else{ }
		}
		if(numberOfFamilyMembers.getValue() == "4+"){
			if(familyInCollege.getValue() == "1"){ }
			else if(familyInCollege.getValue() == "2"){	
				aidPercent += .03;
			}
			else if(familyInCollege.getValue() == "3"){
				aidPercent += .08;
			}
			else if(familyInCollege.getValue() == "4+"){
				aidPercent += .1;
			}
			else{ }
		}
		
		 //Adjust based on income
		if(incomeOne.getValue() == "None"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .42;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .36;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .30;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .24;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .18;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .12;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){
				aidPercent += .06;
			}
		}
		if(incomeOne.getValue() == "Less than $50,000"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .35;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .3;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .25;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .2;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .15;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .1;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){
				aidPercent += .05;
			}
		}
		if(incomeOne.getValue() == "$50,000-$99,999"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .28;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .24;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .2;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .16;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .12;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .08;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){
				aidPercent += .04;
			}
		}
		if(incomeOne.getValue() == "$100,000-$149,999"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .21;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .18;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .15;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .12;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .19;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .06;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){
				aidPercent += .03;
			}
		}
		if(incomeOne.getValue() == "$150,000-$199,999"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .16;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .14;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .12;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .1;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .08;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .04;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){
				aidPercent += .02;
			}
		}
		if(incomeOne.getValue() == "$200,000-249,000"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .13;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .11;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .09;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .07;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .05;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .03;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){
				aidPercent += .01;
			}
		}
		if(incomeOne.getValue() == "$250,000 or more"){
			if(incomeTwo.getValue() == "None"){
				aidPercent += .11;
			}
			if(incomeTwo.getValue() == "Less than $50,000"){
				aidPercent += .09;
			}
			if(incomeTwo.getValue() == "$50,000-$99,999"){
				aidPercent += .07;
			}
			if(incomeTwo.getValue() == "$100,000-$149,999"){
				aidPercent += .05;
			}
			if(incomeTwo.getValue() == "$150,000-$199,999"){
				aidPercent += .03;
			}
			if(incomeTwo.getValue() == "$200,000-249,000"){
				aidPercent += .01;
			}
			if(incomeTwo.getValue() == "$250,000 or more"){ }
		}
		
		 //Adjust based on student's income, but only if possible
		if(aidPercent > .3){
			if(incomeStudent.getValue() == "None"){ }
			if(incomeStudent.getValue() == "Seasonal employment"){
				aidPercent -= .04;
			}
			if(incomeStudent.getValue() == "Part-time employment"){
				aidPercent -= .08;
			}
			if(incomeStudent.getValue() == "Full-time employment"){
				aidPercent -= .12;
			}
		}
		
		 //Multiply tuition by percentage to find what the aid is
		double aid = tuition * aidPercent;
		double aid2 = roundTwoDecimals(aid);
		return aid2;
	}
	
	public double calcAfterAid(double tuition, double aid){ //EFC
		double afterAid = 0.00;
		 //Subtract aid from tuition to find total  after aid
		afterAid = tuition - aid;
		double afterAid2 = roundTwoDecimals(afterAid);
		return afterAid2;
	}
	
	public String calcDifference(double afterAid){ //Left over
		double desiredPay = Integer.parseInt(desiredTuition.getText());
		if(desiredPay < afterAid){ //Only need to subtract if total  after is still greater than desired pay
			double afterDifference = afterAid - desiredPay;
			double afterDifference2 = roundTwoDecimals(afterDifference);
			String difference = "$" + afterDifference2;
			return difference;
		}
		else{ //If total after is less than desired,  no need  to calculate
			String difference = "You have achieved your desired pay.";
			return difference;
		}
	}
	
	public double roundTwoDecimals(double value) { //Rounds double to two decimals (hopefully)
		double decimal = (double)Math.round(value * 1000.00) / 1000.00;
		return decimal;
	}
	
	public void setMainApp(Main Main) {
		this.Main = Main;
	}
	
}