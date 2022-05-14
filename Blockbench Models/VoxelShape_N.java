Stream.of(
Block.createCuboidShape(0, 0, 0, 5, 3, 16),
Block.createCuboidShape(11, 0, 0, 16, 3, 16),
Block.createCuboidShape(1, 3, 12, 4, 9, 15),
Block.createCuboidShape(12, 3, 12, 15, 9, 15),
Block.createCuboidShape(1, 3, 1, 4, 11, 4),
Block.createCuboidShape(12, 3, 1, 15, 11, 4),
Block.createCuboidShape(-1, 11, 0, 17, 16, 4),
Block.createCuboidShape(1, 12, -1, 4, 15, 0),
Block.createCuboidShape(12, 12, -1, 15, 15, 0),
Block.createCuboidShape(1, 7, 8, 4, 10, 14),
Block.createCuboidShape(1, 8, 5, 4, 11, 11),
Block.createCuboidShape(1, 9, 3, 4, 12, 8),
Block.createCuboidShape(12, 8, 5, 15, 11, 11),
Block.createCuboidShape(12, 7, 8, 15, 10, 14),
Block.createCuboidShape(12, 9, 3, 15, 12, 8)
).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();