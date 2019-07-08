
# Binaries
GRADLE              := ./gradlew

###############################################################################################

.PHONY: build
build:
	$(GRADLE) war

.PHONY: test
test:
	$(GRADLE) test