/**
 * Displays the main page.
 */
function AppComponent() {
}

/**
 * Retrieves the Legal Entities data from our REST service.
 */
AppComponent.prototype.init = function() {

	var self = this;
	var request = new XMLHttpRequest();
	request.open('GET', 'api/legal-entities', true);
	request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	request.send();
	request.onload = function() {
		console.log('onload response:', this.status, this.response);

		switch (this.status) {
		case 200:
			self.data = JSON.parse(this.response);
			self.render();
		}
	}
	request.onerror = function() {
		console.log('onerror response:', this.status, this.response);
	}
};

/**
 * Renders the page template.
 */
AppComponent.prototype.render = function() {

	$('#root').html(`
		<div class='container'>
			<div id='legal-entities'></div>
		</div>
	`);

	let legalEntitiesComponent = new LegalEntitiesComponent(this.data);
	legalEntitiesComponent.render();
};
