Stream.of(
Block.createCuboidShape(0, 0, 11, 16, 3, 16),
Block.createCuboidShape(0, 0, 0, 16, 3, 5),
Block.createCuboidShape(12, 3, 12, 15, 9, 15),
Block.createCuboidShape(12, 3, 1, 15, 9, 4),
Block.createCuboidShape(1, 3, 12, 4, 11, 15),
Block.createCuboidShape(1, 3, 1, 4, 11, 4),
Block.createCuboidShape(0, 11, -1, 4, 16, 17),
Block.createCuboidShape(-1, 12, 12, 0, 15, 15),
Block.createCuboidShape(-1, 12, 1, 0, 15, 4),
Block.createCuboidShape(8, 7, 12, 14, 10, 15),
Block.createCuboidShape(5, 8, 12, 11, 11, 15),
Block.createCuboidShape(3, 9, 12, 8, 12, 15),
Block.createCuboidShape(5, 8, 1, 11, 11, 4),
Block.createCuboidShape(8, 7, 1, 14, 10, 4),
Block.createCuboidShape(3, 9, 1, 8, 12, 4)
).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();