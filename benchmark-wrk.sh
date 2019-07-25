#!/usr/bin/env bash
trap 'kill -TERM $PID; exit' SIGINT

usage() {
	echo "$0 <duration> <threads> <connections>"
}
if [[ -z "$1" ]]; then
	usage
	exit 1
fi
if [[ -z "$2" ]]; then
	usage
	exit 2
fi
if [[ -z "$3" ]]; then
	usage
	exit 3
fi

BASEDIR=$(dirname "$0")
for file in "${BASEDIR}"/*.jar ; do
	echo "BENCHMARKING ${file/${BASEDIR}\//}"

	echo "	ASYNC"
	${file} &> /dev/null &
	PID=$!
	DONE=0
	while [[ $DONE -eq 0 ]]; do
		if curl -s http://localhost:8080/ > /dev/null; then
			sleep 1
			wrk -d"$1" -t"$2" -c"$3" http://localhost:8080/async/benchmark/benchmarker/01189998819991197253
			DONE=1
		fi
		sleep 1
	done
	kill -TERM $PID
	wait

	echo "	SYNC"
	${file} &> /dev/null &
	PID=$!
	DONE=0
	while [[ $DONE -eq 0 ]]; do
		if curl -s http://localhost:8080/ > /dev/null; then
			sleep 1
			wrk -d"$1" -t"$2" -c"$3" http://localhost:8080/sync/benchmark/benchmarker/01189998819991197253
			DONE=1
		fi
		sleep 1
	done
	kill -TERM $PID
	wait

	echo ""
done