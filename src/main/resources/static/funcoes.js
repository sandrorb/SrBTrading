function startOnLoad(){
	simular();
	buscaDadosFinanceiros();	
}

//google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(simular);
		
function simular() {
	var data = new google.visualization.DataTable();
    data.addColumn('number', 'X');
    data.addColumn('number', 'Curva de Capital');
      
  	var rentAcumulada = 0.0;
	var    nOperacoes = parseInt( $("#nOperacoes").val() );	
	var             p = parseFloat( $("#p").val() );
	var         risco = parseFloat( $("#risco").val() );
	var            rr = parseFloat( $("#rr").val() );
	
	var     nOpPorMes = parseFloat( $("#nOpPorMes").val() );

	var      ddMax = 0.0;
	var    rentMax = 0.0;
	
	for (var i = 0; i < nOperacoes; i++) {
		if ( (100 * Math.random()) < p ){
			rentAcumulada = rentAcumulada + (rr * risco);
		} else {
			rentAcumulada = rentAcumulada - risco;
		}
		
		if (rentAcumulada > rentMax){ rentMax = rentAcumulada;}
		
		if (Math.abs(rentAcumulada-rentMax) > ddMax) {ddMax = Math.abs(rentAcumulada-rentMax);}
		
		data.addRow([i,rentAcumulada/100.0]);
	}
	
	var nMeses = nOperacoes / nOpPorMes;
	//O cálculo abaixo é para o caso de a rentabilidade acumulada for calculada na base dos juros compostos
	//var rentabilidadeMensal = 100 * (Math.pow(1 + (rentAcumulada / 100.0), (1/nMeses))  - 1);
	var rentabilidadeMensal = rentAcumulada / nMeses;
		
    var options = {
	    	hAxis: {
		        title: 'Número de Operações',
		        titleFontSize: 16,
		        titleTextStyle: {color: 'gray'},
		        fontName: "Calibri",
		        gridlines: {count: 10},
        	},
	        vAxis: {
		        title: 'Rentabilidade Acumulada',
		        titleFontSize: 16,
		        titleTextStyle: {color: 'gray'},
		        fontName: "Calibri",
		        format: "0%"
        	},
        
        	//ddMax.toFixed(2)
        	title: 'Curva de Capital \nDrawdown Máximo: ' + ddMax.toFixed(2) + '% e Rentabilidade Mensal: ' + rentabilidadeMensal.toFixed(2) + '%', 
        	//subtitle: 'Drawdown Máximo: ' + ddMax + '%',
        
			titleTextStyle: {
	            color: 'gray',    // any HTML string color ('red', '#cc00cc')
	            fontName: 'Calibri', // i.e. 'Times New Roman'
	            fontSize: 20, // 12, 18 whatever you want (don't specify px)
	            bold: true,    // true or false
	            italic: true,   // true of false
	            align: 'center',
        	},

        	chartArea: {left: 100, top: 100, width: '80%', height: '65%'},
        
        	curveType: 'function', //linha suavizada, mas parece só funcionar em LineChart e não em AreaChart

		    backgroundColor: {
		    	fill: '#fffee0',
		        fillOpacity: 1.0 //
		    },
		          
		    /*legend: { position: "top", maxLines: 9, alignment:'start'  },*/
		    legend: {position: 'none'},
		}; //fim de options
		      
	//var chart = new google.visualization.LineChart(document.getElementById('chart_simulacao'));
	var chart = new google.visualization.AreaChart(document.getElementById('chart_simulacao'));
	chart.draw(data, options);

	calculaDados();

} // Fim da função simular
		    

function calculaDados(){
	var p = parseFloat( $("#p").val() );
	//var risco = parseFloat( $("#risco").val() );
	var rr = parseFloat( $("#rr").val() ); 
	var breakEven = 100 * (1.0 / (1.0 + rr) );
	var edge = p - breakEven;	    	
	$("#dadosCalculados").text("Breakeven: " + breakEven.toFixed(2) + "%  e Edge: " + edge.toFixed(2) + "%");
}
	    



function buscaDadosFinanceiros(){

	$.get( "https://br.investing.com/indices/bovespa", function( data ) {
		const ibovLastPrice = $(data).find('[data-test="instrument-price-last"]').html();			
		const ibovChangePercent = $(data).find('[data-test="instrument-price-change-percent"]').html();
		$( "#Ibov" ).html( "Ibovespa: " + ibovLastPrice + " " + ibovChangePercent );
    });
	
	
	$.get( "https://br.investing.com/indices/ibovespa-futures", function( data ) {
		const ibovFutureLastPrice = $(data).find('[data-test="instrument-price-last"]').html();
		const ibovFutureChangePercent = $(data).find('[data-test="instrument-price-change-percent"]').html();
		$( "#IbovFuturo" ).html( "Ibovespa Futuro: " + ibovFutureLastPrice + " " + ibovFutureChangePercent );
    });

}	

	    

/* Início: Exemplos de configurações do gráfico */
/*
backgroundColor: {
	gradient: {
    	// Start color for gradient.
    	color1: '#fbf6a7',
       	// Finish color for gradient.
       	color2: '#33b679',
       	// Where on the boundary to start and
       	// end the color1/color2 gradient,
       	// relative to the upper left corner
       	// of the boundary.
       	x1: '0%', y1: '0%',
       	x2: '100%', y2: '100%',
       	// If true, the boundary for x1,
       	// y1, x2, and y2 is the box. If
       	// false, it's the entire chart.
       	useObjectBoundingBoxUnits: true
	},
},
*/         
/*
chartArea: {
	backgroundColor: {
    	fill: 'yellow',
        //opacity: 100,
        fillOpacity: 0.1
	},
},
*/
/* Fim: Exemplos de configurações do gráfico */