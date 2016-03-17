// Setup Publish Date
var element = document.querySelector("#publish-section");
rome(dateInput, { "appendTo": element });

// Setup WYSIHTML5 Editor
var editor = new wysihtml5.Editor("editor", {
    toolbar:     "wysihtml5-editor-toolbar",
    contentEditableMode: true,
    useLineBreaks: false,
    parserRules: wysihtml5ParserRules
});

// Toggle Calendar
var calendarToggle = document.querySelector("#calendar-toggle");
var dateInputField = document.querySelector("#dateInput");
calendarToggle.addEventListener("click", function(event) {
	event.preventDefault();
    dateInputField.classList.toggle('open');
}, false);