Develop software to help the nonprofit automate communication with 
their supporters. A supporter is defined as someone who has signed up to receive emails or 
letters from the nonprofit.   <br/>
<br/>
The nonprofit stores information about their supporters in CSV file. The CSV file is a plain text 
file, containing data organized into columns separated by a comma. The data in each column is 
enclosed in double quotes. The first line of the file contains the headers for each column. <br/>
<br/>

A sample file, containing some of the nonprofit’s supporters’ information, is available on Cavas. 
The CSV file contains first and last name, company name, address, city, county, state, zip, 
phone 1, phone 2, email address, and web page URL.  <br/>
<br/>

The nonprofit would like you to create a program that they can run on the command line. 
The program should take a CSV file and a template (or two) as input, and generate files that will 
contain the email messages and letters to send to their members. When the program is run, it 
should output a new text file per row in the CSV file, with all placeholders replaced with the 
appropriate value for that row. <br/>
<br/>

Note that some options take arguments. For example, --email-template takes a file path 
and --output-dir takes a folder. Where an argument is required, it must immediately follow 
the option. Other options take no arguments and indicate an action e.g. --email indicates that 
email messages should be generated. <br/>
<br/>
 
Also note that some options require other options to also be present. For example, if the 
program is run with the --email option, then the --email-template option (with it’s 
required argument) must also be provided. Calling your program with invalid combinations of 
arguments should generate an error. For example, the following command is illegal because it 
contains --email but --email-template is not provided: <br/>
<br/>
  
A user can request both emails and letters as long as all the necessary inputs are provided. <br/>
<br/>
 
When a user provides an illegal combination of inputs, the program should exit with a helpful 
error message, and a short explanation of how to use the program along with examples. See 
the “Example input and output” section for an example error message. <br/>
<br/>

<pre>
--email Generate email messages. If this option is provided, then --email-template must also be provided. 

--email-template <path/to/file> A filename for the email template. 

--letter Generate letters. If this option is provided, then --letter-template must also be provided. 

--letter-template <path/to/file> A filename for the letter template.

--output-dir <path/to/folder> The folder to store all generated files. This option is required. 

--csv-file <path/to/file> The CSV file to process. This option is required. 
</pre>