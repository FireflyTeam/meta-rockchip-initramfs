#!/bin/sh

SECONDS=5

# Clean tty
clear

show_splash() {
	fbi -T 1 --noverbose /rk/logo.png &>/dev/null
}

clean_splash() {
	killall fbi
}

# Show logo on screen
show_splash

# battery check could be added in here, to stop system
# Wait key event
i=$SECONDS
while [ "$i" -ge 0 ]; do
	printf "\rInit: Hit any key to enter recovery : $i seconds"
	printf "\rInit: Hit any key to enter recovery : $i seconds" >/dev/tty1

	read -s -n 1 -t 1 key
	KEY_PRESS=$?
	ps | grep -v grep | grep -q fbi
	FBI_EXIT=$?
	if [ "$KEY_PRESS" -eq 0 ] || [ "$FBI_EXIT" -ne 0 ]; then
		clean_splash
		log "Recovery is WIP, so falling to the shell"
		exec sh
	fi
	i=$((i - 1))
done

clean_splash
