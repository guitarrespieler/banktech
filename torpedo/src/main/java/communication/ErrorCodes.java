package communication;

public enum ErrorCodes {
	team_not_invited,							//errorcode 1
	already_running_game,						//errorcode 2
	gameID_not_exist,							//errorcode 3
	no_permission_to_manage_this_submarine,		//errorcode 4
	torpedo_cooling_down,						//errorcode 7
	call_before_reload,							//errorcode 8
	game_is_not_running,						//errorcode 9
	submarine_has_already_moved_in_this_round,	//errorcode 10
	too_much_acceleration,						//errorcode 11
	too_sharp_curve								//errorcode 12	
}
