# Readability Score
## About
This program accepts text input from the command line and determines how difficult the text is and for which age it is most suitable. Difficulty levels are determined by the Flesch-Kincaid Readability Test, Coleman-Liau Index, Automated Readability Index, and Simple Measure of Gobbledygook. 
## Example
Users have the option to display the results of all tests or to choose a specific test. At the end of the output, the average age the text can be understood by is printed.
```
java Main in.txt
The text is:
This is the front page of the Simple English Wikipedia. Wikipedias are places where people
work together to write encyclopedias in different languages. We use Simple English words
and grammar here. The Simple English Wikipedia is for everyone! That includes children and
adults who are learning English. There are 142,262 articles on the Simple English
Wikipedia. All of the pages are free to use. They have all been published under both the
Creative Commons License and the GNU Free Documentation License. You can help here! You
may change these pages and make new pages. Read the help pages and other good pages to
learn how to write pages here. If you need help, you may ask questions at Simple talk. Use
Basic English vocabulary and shorter sentences. This allows people to understand normally
complex terms or phrases.
 
Words: 137
Sentences: 14
Characters: 687
Syllables: 210
Polysyllables: 17
Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all
 
Automated Readability Index: 7.08 (about 13 year olds).
Flesch–Kincaid readability tests: 6.31 (about 12 year olds).
Simple Measure of Gobbledygook: 9.42 (about 15 year olds).
Coleman–Liau index: 10.66 (about 17 year olds).
 
This text should be understood in average by 14.25 year olds.
```
