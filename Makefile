run:
	@shadow-cljs compile script && node out/day1.js

.PHONY: test
test:
	@shadow-cljs compile test && node out/node-tests.js

lint:
	@clj-kondo --lint src
