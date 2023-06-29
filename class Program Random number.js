'using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace Random_Number
{
    public partial class Form1 : Form
    {
        // Variable
        int result = 0;

        public Form1()
    {
        InitializeComponent();
    }

    private void generateButton_Click(object sender, EventArgs e)
    {
        try
        {
            // Get how many random numbers the user wants
            int myRandomNumbers = int.Parse(howManyTextBox.Text);

            // Create the random object
            Random rand = new Random();

            for (int i = 0; i < myRandomNumbers; i++)
            {
                // Create the list of random numbers
                result = rand.Next(1, 101);

                // Display the random numbers in the ListBox
                randomNumbersListBox.Items.Add(result);
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }
    }

    private void saveAs_Click(object sender, EventArgs e)
    {
         StreamWriter outputFile;

         if (saveFile.ShowDialog() == DialogResult.OK)
         {
                 // Create the selected file
                 outputFile = File.CreateText(saveFile.FileName);

                 // Write data to the file
                 outputFile.WriteLine(result);

                 // Close the file
                 outputFile.Close();
         }
         else
         {
             MessageBox.Show("Operation Cancelled");
         }
    }

    private void clearButton_Click(object sender, EventArgs e)
    {
        // Clear the ListBox and TextBox
        howManyTextBox.Text = "";
        randomNumbersListBox.Items.Clear();
    }

    private void exitButton_Click(object sender, EventArgs e)
    {
        // Close the program
        this.Close();
    }
}
}