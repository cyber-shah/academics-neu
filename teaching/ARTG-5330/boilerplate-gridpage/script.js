document.addEventListener('DOMContentLoaded', function () {

    d3.select("div#chartId")
    .append("div")
    // Container class to make it responsive.
    .classed("svg-container", true) 
    .append("svg")
    // Responsive SVG needs these 2 attributes and no width and height attr.
    .attr("preserveAspectRatio", "xMinYMin meet")
    .attr("viewBox", "0 0 600 100")
    // Class to make it responsive.
    .classed("svg-content-responsive", true)
    // Fill with a rectangle for visualization.
    .append("rect")
    .classed("rect", true)
    .attr("width", 600)
    .attr("height", 100);


});
