
from tkinter import *
from tkinter import ttk 
#from backend import *
from tkinter import messagebox
from tkinter import tix
import json
import random
from sys import stdout

class glee_config(object):
	
	default_font = ("Courier", 18)
	
	def __init__(self):
		
		# set up initial window 
		root = tix.Tk()
		root.title("Binary Racetrack Question Editor")
		root.geometry('1000x650')
		root.rowconfigure(0, weight=1)
		root.columnconfigure(0,weight=1)
		
		# set up the notebook
		book = ttk.Notebook(root)
		book.pack(fill=BOTH,expand=1)
		
		page_1_frame = ttk.Frame(book)
		page_1_frame.pack(fill=BOTH,expand=1)
		
		page_2_frame = ttk.Frame(book)
		page_2_frame.pack(fill=BOTH,expand=1)
		
		book.add(page_1_frame, text="Edit Questions")
		book.add(page_2_frame, text="Choose Categories")
		
		# set up category frame
		cat_frame = ttk.Frame(page_1_frame)
		cat_frame.pack(side=LEFT,fill=Y)
		cat_label = ttk.Label(cat_frame, text="Categories", font=self.default_font)
		cat_label.pack(pady=3,padx=3)
		load_cat_frame = ttk.Frame(cat_frame)
		load_cat_frame.pack(side=TOP) # Initially this was set to fill=X but removing that allows the buttons to be centered
		load_cat_button = ttk.Button(load_cat_frame, text="Select Category", command=self.cat_select)
		load_cat_button.pack(side=LEFT)
		del_cat_button = ttk.Button(load_cat_frame, text="Delete Category", command=self.cat_delete)
		del_cat_button.pack(side=LEFT)
		
		cat_scroll = Scrollbar(cat_frame)
		cat_scroll.pack(side=RIGHT, fill=Y)
		
		cat_box = Listbox(cat_frame, font=self.default_font, yscrollcommand=cat_scroll.set)
		cat_scroll.config(command=cat_box.yview)
		
		# Double click event is different than button presses, in that the function it calls has to accept
		# an event object.  Because this is object-oriented, that would mean the function would have to 
		# accept self and the event object, and cat_select() only accepts self.  We could use a new function
		# that does nothing but call cat_select(), but instead of that we just create a lambda function.
		# A lambda function is a temporary function that exists only during the time it is called.
		# After that, it is gone.  Lambda functions are often used in locations like this, where a very 
		# short function is wanted for one specific purpose.
		cat_box.bind("<Double-Button-1>", lambda x:self.cat_select()) 
		cat_box.pack(side=TOP, fill=Y,expand=1)
		
		# set up input boxes
		# use a frame for each text/box pair to make layout easier
		# start with a large frame for everything not in cat_frame or but_frame
		
		main_frame = ttk.Frame(page_1_frame)
		main_frame.pack(fill=BOTH,expand=1)
		
		title_frame = ttk.Frame(main_frame)
		title_frame.pack(fill=X)
		title_label = ttk.Label(title_frame, text="Question", font=self.default_font)
		title_label.pack(side=LEFT,pady=3,padx=3)
		title_entry = Text(title_frame, font=self.default_font, height=5, wrap='word')
		#title_entry = Entry(title_frame, font=self.default_font,width=40)
		title_entry.pack(side=RIGHT)
		
		change_cat_frame = ttk.Frame(main_frame)
		change_cat_frame.pack(fill=X)
		change_cat_label = ttk.Label(change_cat_frame, text="Category", font=self.default_font)
		change_cat_label.pack(side=LEFT,pady=3,padx=3)
		change_cat_entry = ttk.Entry(change_cat_frame, font=self.default_font)
		change_cat_entry.pack(side=RIGHT)
		
		cor_ans_frame = ttk.Frame(main_frame)
		cor_ans_frame.pack(fill=X)
		cor_ans_label = ttk.Label(cor_ans_frame, text="Correct Answer", font=self.default_font)
		cor_ans_label.pack(side=LEFT,pady=3,padx=3)
		cor_ans_entry = ttk.Entry(cor_ans_frame, font=self.default_font)
		cor_ans_entry.pack(side=RIGHT)
		
		# for the alternate answers we need another container frame
		# and then two smaller frames
		alt_ans_frame = ttk.Frame(main_frame)
		alt_ans_frame.pack(fill=X)
		
		alt_ans_left_frame = ttk.Frame(alt_ans_frame)
		alt_ans_left_frame.pack(side=LEFT, fill=Y)
		alt_ans_label = ttk.Label(alt_ans_left_frame, text="Alternate Answers", font=self.default_font)
		alt_ans_label.pack(side=TOP,pady=3,padx=3)
		# change alt_ans_warning_label to say "Must have either 2 or 4 answers" 
		# if there are 1 or 3 answers including the alternates
		alt_ans_warning_string = StringVar()
		alt_ans_warning_label = ttk.Label(alt_ans_left_frame, textvariable=alt_ans_warning_string, font=self.default_font)
		alt_ans_warning_label.config(foreground="red")
		alt_ans_warning_label.pack(pady=3,padx=3,side=TOP)
		
		alt_ans_right_frame = ttk.Frame(alt_ans_frame)
		alt_ans_right_frame.pack(side=RIGHT)
		alt_ans_1_entry = ttk.Entry(alt_ans_right_frame, font=self.default_font)
		alt_ans_1_entry.pack(pady=3)
		alt_ans_2_entry = ttk.Entry(alt_ans_right_frame, font=self.default_font)
		alt_ans_2_entry.pack(pady=3)
		alt_ans_3_entry = ttk.Entry(alt_ans_right_frame, font=self.default_font)
		alt_ans_3_entry.pack(pady=3)
		
		# set up frame for buttons 
		but_frame = ttk.Frame(main_frame)
		but_frame.pack(fill=X)
			
		load_quest_button = ttk.Button(but_frame, text="Select Question", command=self.quest_select)
		load_quest_button.pack(side=LEFT)
		save_button = ttk.Button(but_frame, text="Save Question", command=self.save_question)
		#save_button.config(font=self.default_font)
		save_button.pack(side=LEFT)
		undo_button = ttk.Button(but_frame, text="Undo Change", command=self.undo_change)
		#undo_button.config(font=self.default_font)
		undo_button.pack(side=LEFT)
		undo_all_button = ttk.Button(but_frame, text="Undo All Changes", command=self.undo_all_changes)
		#undo_all_button.config(font=self.default_font)
		undo_all_button.pack(side=LEFT)
		new_button = ttk.Button(but_frame, text="New Question", command=self.new_question)
		new_button.pack(side=LEFT)
		del_button = ttk.Button(but_frame, text="Delete Question", command=self.delete_question_func)
		del_button.pack(side=LEFT)
		save_button = ttk.Button(but_frame, text="Save", command=self.save_all)
		save_button.pack(side=LEFT)
		
		# set up frame for question selection
		quest_frame = ttk.Frame(main_frame)
		quest_frame.pack(side=LEFT,fill=BOTH,expand=1)
		
		quest_label = ttk.Label(quest_frame, text="Questions", font=self.default_font)
		quest_label.pack(pady=3,padx=3)
		quest_scroll = Scrollbar(quest_frame)
		quest_scroll.pack(fill=Y, side=RIGHT)
		
		quest_box = Listbox(quest_frame, font=self.default_font, yscrollcommand=quest_scroll.set)
		quest_scroll.config(command=quest_box.yview)
		quest_box.bind("<Double-Button-1>", lambda x:self.quest_select()) 	
		
		#pad_frame = ttk.Frame(quest_frame)
		#pad_frame.pack(side=RIGHT, fill=Y)
		quest_box.pack(fill=BOTH, expand=1)
		
		# work on choose categories tab
		choose_cat_title_frame = ttk.Frame(page_2_frame)
		choose_cat_title_frame.pack(side=TOP)# fill=X)
		
		choose_cat_title = ttk.Label(choose_cat_title_frame, text="Select Categories", font=self.default_font)
		choose_cat_title.pack(side=TOP)
		
		default_cat_frame = ttk.Frame(page_2_frame)
		default_cat_frame.pack(side=LEFT, fill=Y)
		
		default_cat_buffer_frame = ttk.Frame(default_cat_frame)
		default_cat_buffer_frame.pack(side=TOP)
		
		default_cat_title = ttk.Label(default_cat_buffer_frame, text="Default Categories", font=self.default_font)
		default_cat_title.pack(side=TOP)
		
		#user_cat_frame = ttk.Frame(page_2_frame)
		user_cat_master_frame = ttk.Frame(page_2_frame)
		user_cat_master_frame.pack(fill=BOTH, expand=1)
		user_cat_title = ttk.Label(user_cat_master_frame, text="Custom Categories", font=self.default_font)
		user_cat_title.pack(side=TOP)
		
		user_cat_scroll_window = tix.ScrolledWindow(user_cat_master_frame, scrollbar="x")
		user_cat_scroll_window.pack(fill=BOTH, expand=1)
		
		#user_cat_frame = ttk.Frame(user_cat_scroll_window)
		#user_cat_frame.pack(fill=BOTH)#, expand=1)
		user_cat_frame = user_cat_scroll_window.window
		
		#user_cat_scroll = Scrollbar(user_cat_frame, orient=HORIZONTAL)
		#user_cat_scroll.pack(side=BOTTOM, fill=X)
		
		#user_cat_frame.config(yscrollcommand=user_cat_scroll.set)
		
		builtin_cat_checkboxes = {}
		
		#choose_cat_frame = ttk.Frame(page_2_frame)
		#choose_cat_frame.pack(side=TOP)#, fill=X)

		# give all the frames borders for debugging purposes
		#default_cat_frame.config(relief=SUNKEN)
		#choose_cat_title_frame.config(relief=SUNKEN)
		#user_cat_frame.config(relief=SUNKEN)
		#cat_frame.config(relief=SUNKEN)
		#main_frame.config(relief=SUNKEN)
		#title_frame.config(relief=SUNKEN)
		#change_cat_frame.config(relief=SUNKEN)
		#cor_ans_frame.config(relief=SUNKEN)
		#alt_ans_frame.config(relief=SUNKEN)
		#alt_ans_left_frame.config(relief=SUNKEN)
		#alt_ans_right_frame.config(relief=SUNKEN)
		#but_frame.config(relief=SUNKEN)
		
		# set the things from which we will later need data to be 
		# class member variables
		self.root = root
		self.cat_box = cat_box
		self.save_button = save_button
		self.undo_button = undo_button
		self.undo_all_button = undo_all_button
		self.title_entry = title_entry
		self.change_cat_entry = change_cat_entry
		self.cor_ans_entry = cor_ans_entry
		self.alt_ans_warning_label = alt_ans_warning_label
		self.alt_ans_warning_string = alt_ans_warning_string
		self.alt_ans_1_entry = alt_ans_1_entry
		self.alt_ans_2_entry = alt_ans_2_entry
		self.alt_ans_3_entry = alt_ans_3_entry
		self.quest_box = quest_box
		self.builtin_cat_checkboxes = builtin_cat_checkboxes
		self.default_cat_buffer_frame = default_cat_buffer_frame
		self.user_cat_frame = user_cat_frame
		self.page_2_frame = page_2_frame
		
		self.cat_selected = ""
		self.quest_selected = ""
		self.font_color="blue"
		self.builtin_categories = {}
		self.user_categories = {}
		
		self.fill_defaults()
				
		#self.fname = "C:\\Users\\Tim\\SkyDrive\\Documents\\Programming\\GLEE Config\\sample.txt
		self.fname = "sample.txt"
		self.load_from_file(self.fname)
		
		#questions = load_from_file("C:\\Users\\tmcphers\\SkyDrive\\Documents\\Programming\\GLEE Config\\sample.txt")
		self.print_questions()
		#fout = open("C:\\Users\\Tim\\SkyDrive\\Documents\\Programming\\GLEE Config\\sample2.txt", "w")
		#print_questions(questions, f=fout)
		#fout.close()
		#self.questions = questions
		self.init_categories()
		self.init_default_checkboxes()
		self.init_user_checkboxes()
		
		root.mainloop()
		
	def init_default_checkboxes(self):
		for cat in self.builtin_categories:
			tmp_frame = ttk.Frame(self.default_cat_buffer_frame)
			tmp_frame.pack(side=TOP, fill=X)#, expand=1)
			#tmp_frame.config(relief=SUNKEN)
			b = ttk.Checkbutton(tmp_frame, text=cat, var=self.builtin_categories[cat])
			self.builtin_cat_checkboxes[cat] = b
			b.pack(side=LEFT)
		
	def init_user_checkboxes(self):
		#for i in range(400):
			#x = "%6d"%i
			#self.questions["category " + x] = {}
			#self.questions["category " + x]["question " + str(i)] = [str(i), str(i), str(i), str(i)]
		#self.user_cat_frame.destroy()
		##self.user_cat_frame = ttk.Frame(self.page_2_frame)
		#user_cat_title = ttk.Label(self.user_cat_frame, text="Custom Categories", font=self.default_font)
		#user_cat_title.pack(side=TOP)		
		for cat in self.questions:
			if cat not in self.user_categories:
				self.user_categories[cat] = BooleanVar(value=False)
				
		i = 0
		CATEGORIES_PER_PAGE = 25
		f = None
		for cat in sorted(self.user_categories.keys()):
			if cat not in self.questions:
				del self.user_categories[cat]
				continue
			
			if i%CATEGORIES_PER_PAGE == 0:
				i = 0
				f = ttk.Frame(self.user_cat_frame)
				f.pack(side=LEFT, fill=Y)
				#f.config(relief=SUNKEN)
			tmp_frame = ttk.Frame(f)
			tmp_frame.pack(side=TOP, fill=X)
			#tmp_frame.config(relief=SUNKEN)
			b = ttk.Checkbutton(tmp_frame, text=cat, var=self.user_categories[cat])
			b.pack(side=LEFT)
				
			i += 1 #
			
	def fill_defaults(self):
		print("filling defaults")
		for word in sorted(["English", "Math", "History", "Geography"]):
			self.cat_box.insert(END, word)
		for i in range(20):
			for word in ["1. 10x+3", "2. 52/3", "3. 5*5"]:
				self.quest_box.insert(END, word)
				
	def init_categories(self):
		print("init categories")
		self.cat_box.delete(0, END)  # clear the category box
		self.quest_box.delete(0,END) # clear the question box
		# clear all the entry boxes
		self.cor_ans_entry.delete(0,END)
		self.alt_ans_1_entry.delete(0,END)
		self.alt_ans_2_entry.delete(0,END)
		self.alt_ans_3_entry.delete(0,END)
		self.title_entry.delete(0.0,END)
		self.change_cat_entry.delete(0,END)
		
		for key in sorted(self.questions.keys()):   # populate the category box with the categories
			self.cat_box.insert(END, key)
			
	def reset_questions(self):
		print("reset questions")
		self.quest_box.delete(0, END)
		self.quest_selected = ""
			
	def cat_select(self):
		print("cat select")
		select_index = self.cat_box.curselection()[0]
		category = self.cat_box.get(select_index)
		self.cat_selected = category
		self.fill_quest_box(category)
		self.reset_display()
		self.quest_selected = ""
	
	def cat_delete(self):
		print("cat delete")
		prompt = messagebox.askyesno("Delete Category?", "Are you sure you want to delete this category?\nAll questions in this category will be deleted!")
		if prompt == True:
			del self.questions[self.cat_selected]
			self.cat_selected = ""
	
	def fill_quest_box(self, category):
		print("fill question box")
		self.reset_questions()
		for question in sorted(self.questions[category].keys()):
			self.quest_box.insert(END, question)
	
	def reset_display(self):
		print("reset display")
		self.title_entry.delete(0.0, END)
		self.change_cat_entry.delete(0,END)
		self.cor_ans_entry.delete(0,END)
		self.alt_ans_1_entry.delete(0,END)
		self.alt_ans_2_entry.delete(0,END)
		self.alt_ans_3_entry.delete(0,END)
	
	def quest_select(self, q=None):
		print("question select")
		self.reset_display()
		question = q
		if q == None:
			question = self.quest_box.get(self.quest_box.curselection()[0])
			self.quest_selected = question
		
		category = self.cat_selected
		
		print("Category: " + category + " Question: " + question)
		answers = self.questions[category][question]
		self.fill_answers(question, category, answers)
		
	def fill_answers(self, question, category, answers):
		print("fill answers")
		self.title_entry.insert(END, question)
		self.change_cat_entry.insert(END, category)
		self.cor_ans_entry.insert(END, answers[0])
		self.alt_ans_1_entry.insert(END, answers[1])
		self.alt_ans_2_entry.insert(END, answers[2])
		self.alt_ans_3_entry.insert(END, answers[3])
		
	def save_question(self):
		print("save question")
		question = self.title_entry.get(0.0, END)
		category = self.change_cat_entry.get()
		print("(question, category)", (question, category))
		answers = [self.cor_ans_entry.get(), self.alt_ans_1_entry.get(), 
		           self.alt_ans_2_entry.get(), self.alt_ans_3_entry.get()]
		answers = [i for i in answers if i != ''] # clear all blank answers
		
		if question == "":
			print("blank question.  Setting warning")
			self.alt_ans_warning_string.set("Must enter a \nquestion \nto save.")
			alt_ans_warning_label.config(foreground="red")
		elif category == "":
			print("blank category.  Setting warning")
			self.alt_ans_warning_string.set("Must enter a \ncategory\nto save.")
			self.alt_ans_warning_label.config(foreground="red")
		elif len(answers) == 3 or len(answers) < 2:  # 0, 1, or 3 answers; must have 2 or 4
			print("invalid number of answers: " + str(answers))
			self.alt_ans_warning_string.set("Must enter 2 or 4\nanswers to save.")
			self.alt_ans_warning_label.config(foreground="red")
		elif self.cat_selected == "" or self.quest_selected == "":
			# check if the category or question name has not been selected.
			# If it has not, that means the question is new.	
			print("either category or question not selected. (category, question):", (self.cat_selected, self.quest_selected))
			self.insert_question(question, category, answers)
			self.init_categories()
			self.alt_ans_warning_string.set("New question saved!")
			self.alt_ans_warning_label.config(foreground=self.font_color)
		elif self.cat_selected == category and self.quest_selected == question:
			# we just have to change the answer
			print("just changing answer")
			self.insert_question(question, category, answers)
			self.alt_ans_warning_string.set("Answers changed!")
			self.alt_ans_warning_label.config(foreground=self.font_color)
		else:
			print("deleting and re-inserting")
			self.delete_question(self.quest_selected, self.cat_selected)
			self.quest_selected = question
			self.cat_selected = category
			self.insert_question(question, category, answers)
			self.alt_ans_warning_string.set("Question updated!")
			self.alt_ans_warning_label.config(foreground=self.font_color)
		
			
	def insert_question(self, question, category, answers):
		print("insert question")
		if category not in self.questions:
			self.questions[category] = {}
		self.questions[category][question] = answers
		self.init_categories()
		self.fill_quest_box(category)
		self.fill_answers(question, category, answers)
		self.quest_selected = question
		self.cat_selected = category
		
	def undo_change(self):
		self.quest_select(q=self.quest_selected)
		
	def undo_all_changes(self):
		self.alt_ans_warning_string.set("Reloading\nQuestions!")
		self.alt_ans_warning_label.config(foreground=self.font_color)
		self.load_from_file(self.fname)
		self.init_categories()
		self.quest_selected = ""
		self.cat_selected = ""
			
	def delete_question(self, question, category):
		print("delete question")
		del self.questions[category][question]
		print("questions[category] is now ", self.questions[category])
		if len(self.questions[category]) == 0:
			print("deleting")
			del self.questions[category]
		
		print(self.questions)
			
	def delete_question_func(self):
		prompt = messagebox.askyesno("Delete Question?", "Are you sure you want to delete this question?")
		if prompt == True:
			self.delete_question(self.quest_selected, self.cat_selected)
			category = self.cat_selected
			self.init_categories()
			self.cat_selected = category
			self.fill_quest_box(category)
		
	def new_question(self):
		self.reset_display()
		self.quest_selected = ""
		self.cat_selected = ""
		self.alt_ans_warning_string.set("Starting\na New\nQuestion!")
		self.alt_ans_warning_label.config(foreground=self.font_color)

	def save_all(self):
		fout = open(self.fname, "w")
		self.print_questions(f=fout)
		self.alt_ans_warning_string.set("Saved All\nQuestions!")
		self.alt_ans_warning_label.config(foreground=self.font_color)
		fout.close()
				
	def load_from_file(self, fname): # right now the filename is "sample.txt"
		f = open(fname, "r")
		ret = json.load(f)
		self.questions = ret["questions"]
		if "default categories" in ret:
			self.builtin_categories = ret["default categories"]
			for key in self.builtin_categories:
				self.builtin_categories[key] = BooleanVar(value=self.builtin_categories[key])
		else:
			self.builtin_categories = {}
			for i in ["binary to decimal", "decimal to binary", 
		                      "algebra", "addition", "subtraction", "multiplication", "division"]:
				self.builtin_categories[i] = BooleanVar(value=False)
		if "user categories" in ret:
			self.user_categories = ret["user categories"]
			for key in self.user_categories:
				self.user_categories[key] = BooleanVar(value=self.user_categories[key])
		else:
			pass
		
	def print_questions(self, f=stdout):
		dic = {}
		dic["questions"] = self.questions
		dic["default categories"] = {}# = self.builtin_categories
		for key in self.builtin_categories:
			dic["default categories"][key] = self.builtin_categories[key].get()
		dic["user categories"] = {}
		for key in self.user_categories:
			dic["user categories"][key] = self.user_categories[key].get()
			
		json.dump(dic, f, indent=2)
		
x = glee_config()
x.print_questions()