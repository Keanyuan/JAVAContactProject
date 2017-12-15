function listFunc(liNode){
	var ulNode = liNode.getElementsByTagName("ul")[0];
	with (ulNode.style){
		display = display=="block" ? "none" : "block";
	}
}
