document.addEventListener('DOMContentLoaded', function () {
    const data = [
        { name: 'A', value: 30 },
        { name: 'B', value: 80 },
        { name: 'C', value: 45 },
        { name: 'D', value: 60 },
        { name: 'E', value: 40 }
    ];

    const svgWidth = 500, svgHeight = 300;
    const margin = { top: 20, right: 20, bottom: 30, left: 40 };
    const width = svgWidth - margin.left - margin.right;
    const height = svgHeight - margin.top - margin.bottom;

    const svg = d3.select('.bottomL')
        .append('svg')
        .attr('width', '100%') // set the width to 100% of the container
        .attr('height', '100%') // and the height too

        .attr('viewBox', '0 0 ' + svgWidth + ' ' + svgHeight) 
        // think of this like a box withing which you VIEW your svg.. 
        // play around these numbers and you'll start seeing that this controls how you view your svg! 
        // more like a TELESCOPE, that's how I think of it but feel free to experiment! 

        .append('g')
        .attr('transform', 'translate(' + margin.left + ',' + margin.top + ')')

    const x = d3.scaleBand()
        .range([0, width])
        .padding(0.1);
    const y = d3.scaleLinear()
        .range([height, 0]);

    x.domain(data.map(d => d.name));
    y.domain([0, d3.max(data, d => d.value)]);

    svg.selectAll('.bar')
        .data(data)
        .enter().append('rect')
        .attr('class', 'bar')
        .attr('x', d => x(d.name))
        .attr('width', x.bandwidth())
        .attr('y', d => y(d.value))
        .attr('height', d => height - y(d.value));

    svg.append('g')
        .attr('transform', 'translate(0,' + height + ')')
        .call(d3.axisBottom(x));

    svg.append('g')
        .call(d3.axisLeft(y));
});
