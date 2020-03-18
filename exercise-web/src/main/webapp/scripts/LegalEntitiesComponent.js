/**
 * Displays the Legal Entities.
 */
function LegalEntitiesComponent(legalEntities) {
	this.legalEntities = legalEntities;
}

LegalEntitiesComponent.prototype.render = function() {

	$('#legal-entities').html(`
		<h2>LegalEntities</h2>
		<table id='legal-entities' class='table table-bordered table-hover'>
			<thead>
				<tr>
					<th>Name</th>
					<th>Date of Incorporation</th>
					<th>Country</th>
					<th>Total Shares</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<div id='shareholders'></div>
	`);

	for (let legalEntity of this.legalEntities) {
		let legalEntityRow = `
			<tr>
				<td>${legalEntity.name}</td>
				<td>${legalEntity.incorporationDate}</td>
				<td>${legalEntity.country}</td>
				<td>${legalEntity.totalShares}</td>
			</tr>
		`;

		$(legalEntityRow).appendTo('#legal-entities tbody').on('click', function() {
			let shareholdersComponent = new ShareholdersComponent(legalEntity.shareholders);
			shareholdersComponent.render();
		}.bind(this));
	}
};
