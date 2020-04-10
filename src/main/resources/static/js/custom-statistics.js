/**
 * 
 */
window.onload = function() {
	numberOfDebtorsChart();
	numberOfGivenLoansChart();
	numberOfloansPaymentPassPaymentDateAndPendingChart();
	numberOfloansPaymentPassPaymentDateAndPaidChart();
	loansByCurrency();
};

function numberOfDebtorsChart() {
	const percent = 100;
	const color = '#00BFFE';
	const canvas = 'number-debtors-chart';

	const percentValue = percent; // Sets the single percentage value
	const colorGreen = color, // Sets the chart color
	    animationTime = '1400'; // Sets speed/duration of the animation

	const chartCanvas = document.getElementById(canvas) // Sets canvas element by ID
	   
	// Create a new Chart object
	const doughnutChart = new Chart(chartCanvas, {
	    type: 'doughnut', // Set the chart to be a doughnut chart type
	    data: {
	        datasets: [
	            {
	                data: [percentValue], // Set the value shown in the chart as a percentage (out of 100)
	                backgroundColor: [colorGreen], // The background color of the filled chart
	                borderWidth: 0 // Width of border around the chart
	            }
	        ]
	    },
	    options: {
	        cutoutPercentage: 84, // The percentage of the middle cut out of the chart
	        responsive: false, // Set the chart to not be responsive
	        tooltips: {
	            enabled: false // Hide tooltips
	        }
	    }
	});

	Chart.defaults.global.animation.duration = animationTime; // Set the animation duration
}


function numberOfGivenLoansChart() {
	const percent = 100;
	const color = '#01713c';
	const canvas = 'given-loans-chart';

	const percentValue = percent; // Sets the single percentage value
	const colorGreen = color, // Sets the chart color
	    animationTime = '1400'; // Sets speed/duration of the animation

	const chartCanvas = document.getElementById(canvas) // Sets canvas element by ID
	   
	// Create a new Chart object
	const loansChart = new Chart(chartCanvas, {
	    type: 'doughnut', // Set the chart to be a doughnut chart type
	    data: {
	        datasets: [
	            {
	                data: [percentValue], // Set the value shown in the chart as a percentage (out of 100)
	                backgroundColor: [colorGreen], // The background color of the filled chart
	                borderWidth: 0 // Width of border around the chart
	            }
	        ]
	    },
	    options: {
	        cutoutPercentage: 84, // The percentage of the middle cut out of the chart
	        responsive: false, // Set the chart to not be responsive
	        tooltips: {
	            enabled: false // Hide tooltips
	        }
	    }
	});

	Chart.defaults.global.animation.duration = animationTime; // Set the animation duration
	Chart.defaults.global.animation.easing='easeInOutQuint';
}


function numberOfloansPaymentPassPaymentDateAndPendingChart() {
	const percent = (loansPaymentPassPaymentDateAndPending/numberGivenLoans)*100;
	const color = 'red';
	const canvas = 'given-passpayment-pending-chart';

	const percentValue = percent; // Sets the single percentage value
	const colorGreen = color, // Sets the chart color
	    animationTime = '1400'; // Sets speed/duration of the animation

	const chartCanvas = document.getElementById(canvas) // Sets canvas element by ID
	   
	// Create a new Chart object
	const PaymentPassPaymentDateAndPending = new Chart(chartCanvas, {
	    type: 'doughnut', // Set the chart to be a doughnut chart type
	    data: {
	        datasets: [
	            {
	                data: [percentValue, 100 - percentValue], // Set the value shown in the chart as a percentage (out of 100)
	                backgroundColor: [colorGreen], // The background color of the filled chart
	                borderWidth: 0 // Width of border around the chart
	            }
	        ]
	    },
	    options: {
	        cutoutPercentage: 84, // The percentage of the middle cut out of the chart
	        responsive: false, // Set the chart to not be responsive
	        tooltips: {
	            enabled: false // Hide tooltips
	        }
	    }
	});

	Chart.defaults.global.animation.duration = animationTime; // Set the animation duration
	Chart.defaults.global.animation.easing='easeInOutQuint';
}

function numberOfloansPaymentPassPaymentDateAndPaidChart() {
	const percent = (loansPaymentPassPaymentDateAndPaid/numberGivenLoans)*100;
	const color = 'yellow';
	const canvas = 'given-passpayment-paid-chart';

	const percentValue = percent; // Sets the single percentage value
	const colorGreen = color, // Sets the chart color
	    animationTime = '1400'; // Sets speed/duration of the animation

	const chartCanvas = document.getElementById(canvas) // Sets canvas element by ID
	   
	// Create a new Chart object
	const PaymentPassPaymentDateAndPending = new Chart(chartCanvas, {
	    type: 'doughnut', // Set the chart to be a doughnut chart type
	    data: {
	        datasets: [
	            {
	                data: [percentValue, 100 - percentValue], // Set the value shown in the chart as a percentage (out of 100)
	                backgroundColor: [colorGreen], // The background color of the filled chart
	                borderWidth: 0 // Width of border around the chart
	            }
	        ]
	    },
	    options: {
	        cutoutPercentage: 84, // The percentage of the middle cut out of the chart
	        responsive: false, // Set the chart to not be responsive
	        tooltips: {
	            enabled: false // Hide tooltips
	        }
	    }
	});

	Chart.defaults.global.animation.duration = animationTime; // Set the animation duration
	Chart.defaults.global.animation.easing='easeInOutQuint';
}


function loansByCurrency() {
	/*var x = Object.assign({}, lendMoneyGroupedByCurrency)*/
	console.log(lendMoneyGroupedByCurrency);
//	for (let [key, value] of Object.entries(lendMoneyGroupedByCurrency)) {
//		  console.log(`${key}: ${value}`);
//		}
	
	console.log();
	var ctx = document.getElementById('loans-by-currency').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'horizontalBar',
	    data: {
	        labels: Object.keys(lendMoneyGroupedByCurrency),
	        datasets: [{
	            label: 'Amount of money given by currency',
	            data: Object.values(lendMoneyGroupedByCurrency),
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.8)',
	                'rgba(54, 162, 235, 0.8)',
	                'rgba(255, 206, 86, 0.8)',
	                'rgba(75, 192, 192, 0.8)',
	                'rgba(153, 102, 255, 0.8)',
	                'rgba(255, 159, 64, 0.8)',
	                'rgba(153, 102, 255, 0.8)',
	                'rgba(255, 206, 86, 0.8)'
	                
	            ],
	            borderColor: [
	                'rgba(255, 99, 132, 1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
}


