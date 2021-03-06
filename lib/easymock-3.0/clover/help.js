/**
The file holds the help text for clover HTML reports.
**/

//src file page
var help_select_all = "Selects all tests for this file, highlighting their coverage.";
var help_deselect_all = "Deselects all tests for this file.";
var help_hilight_test = "Highlight covered lines contributed per test.";
var help_test_contribution = "The percentage of coverage contributed by each single test.";
var help_src_file_test = "View the test's summary page or source page.";
var help_src_file_show_tests = "Display a list of tests that contributed to the coverage in this file.";
var help_src_file_expandall_summaries = "Toggles all method summaries in the table below.";
var help_src_file_expandall_src = "Toggles all methods in the source to be either expanded or collapsed.";

// header stats
var help_stmts = "Total number of statements in this ";
var help_loc = "Total number of lines of code in this ";
var help_ncloc = "Total number of non-comment lines of code in this ";

var help_branches = "Total number of branches in this ";
var help_methods = "Total number of methods in this ";
var help_classes = "Total number of classes in this ";
var help_packages = "Total number of packages in this ";
var help_files = "Total number of files in this ";
var help_cmp = "Cyclomatic complexity is the number of paths in this ";
var help_cmp_density = "Complexity density is the complexity divided by the number of statements in this ";
var help_avg_method_cmp = "Complexity divided by the number of methods in this ";
var help_stmts_per_method = "Number of statements per method.";
var help_methods_per_class = "Number of methods per class.";
var help_classes_per_package = "Number of classes per package.";
var help_filter_stats = "Include or exclude filtered elements from these statistics.";


// test pages
var help_test_status = "The test result. Either a Pass, Fail or Error.";
var help_test_time = "The total time in seconds taken to run this test.";
var help_tests_time = "The total time in seconds taken to run this set of tests.";
var help_test_message = "A failure or error message if the test is not successful.";
var help_test_target_class = "A class that was directly hit by this test.";
var help_total_unique_coverage = "The percentage of this test's coverage that is unique.";
var help_unique_coverage = "The percentage of coverage that is uniquely generated by this test.";

// dashboard help
var help_dash_coverage = "A summary of the projects overall coverage.";
var help_dash_test_results = "A summary of results of all tests that were run.";
var help_dash_packages = "The most complex packages in the project.";
var help_dash_classes = "The most complex classes in the project.";
var help_dash_risks = "A list of classes that have the highest complexity and least coverage. These pose the greatest potential risk to the project.";
var help_dash_untested_methods = "A list of methods least tested, ranked in decreasing complexity.";
var help_dash_treemap = "A hierarchical visualisation of coverage per-package. Click through to view class level map.";

var help_pkg_risks = "Big red classes have high complexity and low coverage. These classes represent the biggest potential risks.";
var help_pkg_quick_wins = "Big red classes contain the highest number of untested elements. Overall coverage will be improved the most by covering these classes.";
var help_treemap = "A hierarchical visualisation of this project's coverage. Size denotes number of elements, color denotes coverage. This is useful for spotting untested clusters of code.";

var helpClassRegexp = /\bhelpItem\b/;
var helpClass = 'helpItem';

var showHelpVisitor = function(ele) {
    ele.onmouseover = mouseOverHelp;
    ele.onmouseout = hideMouseOverHelp;
    ele.className = ele.className + ' helpItem';

}
var hideHelpVisitor = function(ele) {
    ele.onmouseover = function(e) {};
    ele.onmouseout =  function(e) {};
    replaceClass(ele, helpClassRegexp, '');
}


function visitAllHelpElements(visitor) {
    // all this to workaround the IE7 getElementsByName bug.
    var helpLabels = document.getElementsByTagName('label');
    for (var i = 0; i < helpLabels.length; i++) {
        var helpLabel = helpLabels[i];
        if (helpLabel.onkeyup) {
            visitor(helpLabel);
        }
    }
}

function toggleHelp(ele) {

    var helpVisitor;
    if (ele.innerHTML == 'HIDE HELP') {
        ele.innerHTML = 'SHOW HELP'
        helpVisitor = hideHelpVisitor;
        toggleLegend();        
    } else {
        ele.innerHTML = 'HIDE HELP'
        helpVisitor = showHelpVisitor;
        toggleLegend();
    }
    visitAllHelpElements(helpVisitor);
}

function toggleLegend() {
    srcLegend = document.getElementById('legend');
    if (srcLegend) {
        showLegend = srcLegend.style.display == 'none';
        srcLegend.style.display = showLegend ? '' : 'none';
    }
}

function hideMouseOverHelp(e) {
    if (!e) var e = window.event;
    nd();
}

function mouseOverHelp(e) {
    if (!e) var e = window.event;
    var target = e.target ? e.target : e.srcElement;
    return overlib(target.onkeyup(), TEXTSIZE, 3, SHADOW, SHADOWOPACITY, 80, BGCLASS, "helpOverBG", FGCLASS, "helpOverFG", SNAPY, 5, ABOVE, CENTER);
}
