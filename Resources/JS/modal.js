function changemodaldisplay(clicked_id){
    let display = document.querySelector('.modal');
    let compStyles = window.getComputedStyle(display);
	if(clicked_id == "close"){
        document.getElementById('modal').style.display='none';
    }
    else if(compStyles.getPropertyValue("display") == "none"){
        console.log(compStyles.getPropertyValue("display"));
		console.log(clicked_id);
        console.log(compStyles.getPropertyValue("display") == "none");
        document.getElementById('modal').style.display='flex';
		
		if("mybtn" == clicked_id){
			document.getElementById('descriptionUpdater').style.display='block';
			document.getElementById('pfpupdater').style.display='none';
		}
		if("pfp" == clicked_id){
			document.getElementById('descriptionUpdater').style.display='none';
			document.getElementById('pfpupdater').style.display='block';
		}
    }
   
}

