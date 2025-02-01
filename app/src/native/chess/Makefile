CXX = clang++
CXXFLAGS += -Weverything -Wno-c++98-compat -Wno-pre-c++20-compat
CXXFLAGS += -std=c++23 -stdlib=libc++ -fmodules-ts -Wall -O2

SRC_DIR = src
BUILD_DIR = build
LIB_DIR = lib

MODULES = $(BUILD_DIR)/ChessEngine.pcm $(BUILD_DIR)/Board.pcm $(BUILD_DIR)/Move.pcm $(BUILD_DIR)/Piece.pcm

SRCS = $(SRC_DIR)/ChessEngine.cppm $(SRC_DIR)/Board.cppm $(SRC_DIR)/Move.cppm $(SRC_DIR)/Piece.cppm

OBJS = $(BUILD_DIR)/ChessEngine.o $(BUILD_DIR)/Board.o $(BUILD_DIR)/Move.o $(BUILD_DIR)/Piece.o

TARGET = $(LIB_DIR)/libchess.so

$(TARGET): $(OBJS)
	$(CXX) -shared -o $@ $^ $(CXXFLAGS)

$(BUILD_DIR)/%.pcm: $(SRC_DIR)/%.cppm
	$(CXX) $(CXXFLAGS) --precompile -o $@ $<

$(BUILD_DIR)/%.o: $(SRC_DIR)/%.cpp
	$(CXX) $(CXXFLAGS) -fmodule-file=$(BUILD_DIR)/ChessEngine.pcm -fmodule-file=$(BUILD_DIR)/Board.pcm -fmodule-file=$(BUILD_DIR)/Move.pcm -fmodule-file=$(BUILD_DIR)/Piece.pcm -c $< -o $@

clean:
	rm -f $(OBJS) $(MODULES) $(TARGET)

.PHONY: clean
