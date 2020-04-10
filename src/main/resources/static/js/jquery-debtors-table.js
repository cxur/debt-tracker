
$(document).ready(function() {
	$('#debtors-information').DataTable({

		"scrollY" : 400,
		"scrollX" : 500,
		"sAjaxSource" : "/getDebtorsData",
		"sAjaxDataProp" : "",
		"columnDefs" : [ {

			"targets" : "_all",
			"defaultContent" : ""
		}

		],
		"aoColumns" : [ {
			"mData" : ""
		},{
			"mData" : "firstName"
		}, {
			"mData" : "lastName"
		}, {
			"mData" : "emailAddress"
		}, {
			"mData" : "phoneNumber"
		}, {
			"mData" : "company"
		}, {
			"mData" : "country"
		},
		{
			"mData" : ""
		},],

		"rowCallback" : function(row, data) {
			if (data.debtorPicture) {
				$('td:eq(0)', row).html(
						'<img alt=""  class= "debtor-profile-pic" src="data:image/png;base64,'+ data.debtorPicture +'">'		
				);
			} else {
				$('td:eq(0)', row).html(
						'<svg class= "debtor-profile-pic"  xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24"><path d="M12 2c2.757 0 5 2.243 5 5.001 0 2.756-2.243 5-5 5s-5-2.244-5-5c0-2.758 2.243-5.001 5-5.001zm0-2c-3.866 0-7 3.134-7 7.001 0 3.865 3.134 7 7 7s7-3.135 7-7c0-3.867-3.134-7.001-7-7.001zm6.369 13.353c-.497.498-1.057.931-1.658 1.302 2.872 1.874 4.378 5.083 4.972 7.346h-19.387c.572-2.29 2.058-5.503 4.973-7.358-.603-.374-1.162-.811-1.658-1.312-4.258 3.072-5.611 8.506-5.611 10.669h24c0-2.142-1.44-7.557-5.631-10.647z"/></svg>'		
				);
				
			}
			
			$('td:eq(7)', row).html(
					'<div class="row"><div class="col-sm"><a href="#" data-debtor='+JSON.stringify(data)+' data-tooltip="tooltip" title="Edit Debtor\'s info" data-toggle="modal" data-target="#exampleModalCenter"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24"><path d="M18.363 8.464l1.433 1.431-12.67 12.669-7.125 1.436 1.439-7.127 12.665-12.668 1.431 1.431-12.255 12.224-.726 3.584 3.584-.723 12.224-12.257zm-.056-8.464l-2.815 2.817 5.691 5.692 2.817-2.821-5.693-5.688zm-12.318 18.718l11.313-11.316-.705-.707-11.313 11.314.705.709z"/></svg></a></div><div class="col-sm"><a href="/debtPaymentDetails/'+JSON.stringify(data.debtorId)+'" data-tooltip="tooltip" title="See payment info"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm1 18h-2v-8h2v8zm-1-12.25c.69 0 1.25.56 1.25 1.25s-.56 1.25-1.25 1.25-1.25-.56-1.25-1.25.56-1.25 1.25-1.25z"/></svg></a></div></div>');
		},

		"fixedHeader" : true,
	});
	
	$('[data-tooltip="tooltip"]').tooltip(); 
	
	$('#exampleModalCenter').on('show.bs.modal', function (event) {
		 var eventData = $(event.relatedTarget);
		 var debtorData = eventData.data('debtor');
		// console.log(debtorData);
		 var modal = $(this);
		 var  profileImg = '<svg style="width: 100px; height: 100px" class= "debtor-profile-pic"  xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24"><path d="M12 2c2.757 0 5 2.243 5 5.001 0 2.756-2.243 5-5 5s-5-2.244-5-5c0-2.758 2.243-5.001 5-5.001zm0-2c-3.866 0-7 3.134-7 7.001 0 3.865 3.134 7 7 7s7-3.135 7-7c0-3.867-3.134-7.001-7-7.001zm6.369 13.353c-.497.498-1.057.931-1.658 1.302 2.872 1.874 4.378 5.083 4.972 7.346h-19.387c.572-2.29 2.058-5.503 4.973-7.358-.603-.374-1.162-.811-1.658-1.312-4.258 3.072-5.611 8.506-5.611 10.669h24c0-2.142-1.44-7.557-5.631-10.647z"/></svg>';
		 
		 if(debtorData.debtorPicture) {
			 profileImg = '<img alt=""  style="width: 100px; height: 100px" class= "debtor-profile-pic" src="data:image/png;base64,'+ debtorData.debtorPicture +'">';
		 } 
		 modal.find('#edit-profile-pic').removeData();
		 modal.find('#edit-profile-pic').append(profileImg);
		 modal.find('#firstNameUpdate').val(debtorData.firstName);
		 modal.find('#lastNameUpdate').val(debtorData.lastName);
		 modal.find('#emailAddressUpdate').val(debtorData.emailAddress);
		 modal.find('#countryUpdate').val(debtorData.country);
		 modal.find('#phoneNumberUpdate').val(debtorData.phoneNumber);
		 modal.find('#companyUpdate').val(debtorData.company);
		 modal.find('#debtorInfoId').val(debtorData.debtorId);
		 modal.find('#debtorProfilePicDB').val(debtorData.debtorPicture);
		 
	});
	
	$('#exampleModalCenter').on('hidden.bs.modal', function () {
		$(this).find('#edit-profile-pic').find('img').remove();
		$(this).find('#edit-profile-pic').find('svg').remove()	
		});

	
});




